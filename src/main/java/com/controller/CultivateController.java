package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.CultivateEntity;

import com.service.CultivateService;
import com.entity.view.CultivateView;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 培训
 * 后端接口
 * @author
 * @email
 * @date
*/
@RestController
@Controller
@RequestMapping("/cultivate")
public class CultivateController {
    private static final Logger logger = LoggerFactory.getLogger(CultivateController.class);

    @Autowired
    private CultivateService cultivateService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service
    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        String role = String.valueOf(request.getSession().getAttribute("role"));
        YonghuEntity yonghuEntity = yonghuService.selectById((Integer) request.getSession().getAttribute("userId"));
        if(role == null || "".equals(role) ){
            return R.error(511,"您没有权限查看");
        }else if (yonghuEntity == null ){
            return R.error(511,"当前登录账户为空");
        }else if ("员工".equals(role)){//员工只能查看自己的
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }else if ("部门主管".equals(role)){//主管可以查看当前部门下员工的
            Integer bumenTypes = yonghuEntity.getBumenTypes();
            params.put("roleTypes",1);//部门主管只能查看员工列表
            params.put("bumenTypes",bumenTypes);//部门主管只能查看当前部门列表
        }else if ("总经理".equals(role)){
            params.put("roleTypes1111",1);//总经理不能查看总经理的
        }
        //管理员能查看全部

        params.put("orderBy","id");
		PageUtils page = cultivateService.queryPage(params);


        //字典表数据转换
		List<CultivateView> list =(List<CultivateView>)page.getList();

        if ("部门主管".equals(role)){//主管需要查看自己的培训
		List<CultivateEntity> list1 = cultivateService.selectList(new EntityWrapper<CultivateEntity>().eq("yonghu_id", request.getSession().getAttribute("userId")));//查询当前用户的培训
            for(CultivateEntity l:list1){
                CultivateView view = new CultivateView();
                BeanUtils.copyProperties( l , view);//把entity封装在view中
                BeanUtils.copyProperties( yonghuEntity , view ,new String[]{ "id", "createDate"});//把用户信息封装在view中
                list.add(view);//放入list中
            }
        }
        for(CultivateView c:list){
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }
    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        CultivateEntity cultivate = cultivateService.selectById(id);
        if(cultivate !=null){
            //entity转view
            CultivateView view = new CultivateView();
            BeanUtils.copyProperties( cultivate , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(cultivate.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
            }
            //字典表字典转换
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody CultivateEntity cultivate, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,cultivate:{}",this.getClass().getName(),cultivate.toString());
        // 获取相差的天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cultivate.getOnTime());
        long timeInMillis1 = calendar.getTimeInMillis();
        calendar.setTime(cultivate.getDownTime());
        long timeInMillis2 = calendar.getTimeInMillis();
        if(timeInMillis2<timeInMillis1){
            return R.error("培训结束时间小于培训开始时间");
        }
        Long betweenDays =  (timeInMillis2 - timeInMillis1) / (1000L*3600L*24L);
        cultivate.setDayNumber(betweenDays.intValue());
        cultivateService.insert(cultivate);
        return R.ok();

    }

    /**
    * 修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody CultivateEntity cultivate, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,cultivate:{}",this.getClass().getName(),cultivate.toString());
        // 获取相差的天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cultivate.getOnTime());
        long timeInMillis1 = calendar.getTimeInMillis();
        calendar.setTime(cultivate.getDownTime());
        long timeInMillis2 = calendar.getTimeInMillis();
        if(timeInMillis2<timeInMillis1){
            return R.error("培训结束时间小于培训开始时间");
        }
        Long betweenDays =  (timeInMillis2 - timeInMillis1) / (1000L*3600L*24L);
        System.out.println(betweenDays);
        cultivate.setDayNumber(betweenDays.intValue());
        cultivateService.updateById(cultivate);//根据id更新
        return R.ok();
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        cultivateService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }



}

