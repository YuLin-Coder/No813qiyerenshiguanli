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

import com.entity.XinziEntity;

import com.service.XinziService;
import com.entity.view.XinziView;
import com.service.YonghuService;
import com.entity.YonghuEntity;

import com.utils.PageUtils;
import com.utils.R;

/**
 * 薪资
 * 后端接口
 * @author
 * @email
 * @date 2021-04-12
*/
@RestController
@Controller
@RequestMapping("/xinzi")
public class XinziController {
    private static final Logger logger = LoggerFactory.getLogger(XinziController.class);

    @Autowired
    private XinziService xinziService;


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
        PageUtils page = xinziService.queryPage(params);

        //字典表数据转换
        List<XinziView> list =(List<XinziView>)page.getList();
        if ("部门主管".equals(role)){//主管需要查看自己的考勤
            List<XinziEntity> list1 = xinziService.selectList(new EntityWrapper<XinziEntity>().eq("yonghu_id", request.getSession().getAttribute("userId")));//查询当前用户的奖惩
            for(XinziEntity l:list1){
                XinziView view = new XinziView();
                BeanUtils.copyProperties( l , view);//把entity封装在view中
                BeanUtils.copyProperties( yonghuEntity , view ,new String[]{ "id", "createDate"});//把用户信息封装在view中
                list.add(view);//放入list中
            }
        }
        for(XinziView c:list){
            //修改对应字典表字段
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
        XinziEntity xinzi = xinziService.selectById(id);
        if(xinzi !=null){
            //entity转view
            XinziView view = new XinziView();
            BeanUtils.copyProperties( xinzi , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(xinzi.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
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
    public R save(@RequestBody XinziEntity xinzi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xinzi:{}",this.getClass().getName(),xinzi.toString());
        Wrapper<XinziEntity> queryWrapper = new EntityWrapper<XinziEntity>()
            .eq("yonghu_id", xinzi.getYonghuId())
            .eq("month", xinzi.getMonth())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XinziEntity xinziEntity = xinziService.selectOne(queryWrapper);
        if(xinziEntity==null){
            xinzi.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      xinzi.set
        //  }
            xinziService.insert(xinzi);
            return R.ok();
        }else {
            return R.error(511,"该员工该月份已经有薪资记录");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XinziEntity xinzi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xinzi:{}",this.getClass().getName(),xinzi.toString());
        //根据字段查询是否有相同数据
        Wrapper<XinziEntity> queryWrapper = new EntityWrapper<XinziEntity>()
            .notIn("id",xinzi.getId())
            .andNew()
            .eq("yonghu_id", xinzi.getYonghuId())
            .eq("month", xinzi.getMonth())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XinziEntity xinziEntity = xinziService.selectOne(queryWrapper);
        if(xinziEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      xinzi.set
            //  }
            xinziService.updateById(xinzi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"该员工该月份已经有薪资记录");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        xinziService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }



}

