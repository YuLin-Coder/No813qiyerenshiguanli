package com.controller;


import java.text.DateFormat;
import java.text.ParseException;
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

import com.entity.ChuqinEntity;

import com.service.ChuqinService;
import com.entity.view.ChuqinView;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 考勤
 * 后端接口
 * @author
 * @email
 * @date 2021-02-25
*/
@RestController
@Controller
@RequestMapping("/chuqin")
public class ChuqinController {
    private static final Logger logger = LoggerFactory.getLogger(ChuqinController.class);

    @Autowired
    private ChuqinService chuqinService;


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
            params.put("roleTypes",1);//部门主管只能查看员工
            params.put("bumenTypes",bumenTypes);//部门主管只能查看当前部门列表
        }else if ("总经理".equals(role)){
            params.put("roleTypes1111",1);//总经理不能查看总经理的
        }
        //管理员能查看全部


        //管理员能查看全部
        params.put("orderBy","id");
        PageUtils page = chuqinService.queryPage(params);


        //字典表数据转换
        List<ChuqinView> list =(List<ChuqinView>)page.getList();//获取查出来的数据
        if ("部门主管".equals(role)){//主管需要查看自己的考勤
            //查看当前用户的考勤
            List<ChuqinEntity> list1 = chuqinService.selectList(new EntityWrapper<ChuqinEntity>().eq("yonghu_id", request.getSession().getAttribute("userId")));
            for(ChuqinEntity l:list1){//循环entity
                ChuqinView chuqinView = new ChuqinView();
                BeanUtils.copyProperties( l , chuqinView);//把entity封装在view中
                BeanUtils.copyProperties( yonghuEntity , chuqinView ,new String[]{ "id", "createDate"});//把用户信息封装在view中
                list.add(chuqinView);//放入list中
            }
        }
        for(ChuqinView c:list){
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
        ChuqinEntity chuqin = chuqinService.selectById(id);
        if(chuqin !=null){
            //entity转view
            ChuqinView view = new ChuqinView();
            BeanUtils.copyProperties( chuqin , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(chuqin.getYonghuId());
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
    * 打卡
    */
    @RequestMapping("/clockIn")
    public R clockIn(String flag, HttpServletRequest request){
        logger.debug("clockIn方法:,,Controller:{},,flag:{}",this.getClass().getName(),flag);
        try {
            Integer id = (Integer)request.getSession().getAttribute("userId");
            String role = String.valueOf(request.getSession().getAttribute("role"));
            if(StringUtil.isEmpty(role) || "管理员".equals(role)){
                return R.error(511,"没有打卡权限");
            }
            Date d = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String date = format1.format(d);
            List<ChuqinEntity> chuqinList = new ArrayList<ChuqinEntity>();//要生成的list数据
            List<String> s = new ArrayList<>();
            s.add("yonghu_id+0");
            Wrapper<ChuqinEntity> queryWrapper3 = new EntityWrapper<ChuqinEntity>().eq("yonghu_id",id).orderDesc(s);
            List<ChuqinEntity> oldChuqinList = chuqinService.selectList(queryWrapper3);
            if("1".equals(flag)){
                //上班卡
                Date date1 = new Date();
                date1.setHours(8);
                date1.setMinutes(0);
                date1.setSeconds(0);
                //上班打卡
                //新增前先查看当前用户最大打卡时间

                if(oldChuqinList != null && oldChuqinList.size()>0){
                    ChuqinEntity entity = oldChuqinList.get(0);
                    String today = entity.getToday();//获取出勤表最大出勤
                    //把日期加一天
                    Date todayTime = format1.parse(today);
                    Calendar   calendar = new GregorianCalendar();
                    calendar.setTime(todayTime);
                    calendar.add(calendar.DATE,1);
                    String newToday = format1.format(calendar.getTime());
                    if(date.equals(today)){
                        return R.error(511,"已经打过上班卡了");
                    }else if(!date.equals(newToday)){//当天日期 不是出勤最大日期加一天的话   就是补充缺勤日期
                        chuqinList = this.getQueQin(d, format1, today, id);
                    }

                    if(chuqinList !=null && chuqinList.size() >0){
                        chuqinService.insertBatch(chuqinList);
                    }


                    //插入当天的上班卡
                    ChuqinEntity chuqin = new ChuqinEntity();
                    chuqin.setOnTime(d);
                    if(d.compareTo(date1)>0){//当前时间d 大于规定时间date1
                        chuqin.setChuqinTypes(6);//设置为迟到
                    }else if(d.compareTo(date1)<=0){//当前时间d 小于等于规定时间date1
                        chuqin.setChuqinTypes(3);//设置为未打下班卡
                    }
                    chuqin.setCreateTime(d);
                    chuqin.setToday(date);
                    chuqin.setYonghuId(id);
                    chuqinService.insert(chuqin);
                }else{
                    //第一次打卡
                    ChuqinEntity chuqin = new ChuqinEntity();
                    chuqin.setOnTime(d);
                    if(d.compareTo(date1)>0){//当前时间d 大于规定时间date1
                        chuqin.setChuqinTypes(6);//设置为迟到
                    }else if(d.compareTo(date1)<=0){//当前时间d 小于等于规定时间date1
                        chuqin.setChuqinTypes(3);//设置为未打下班卡
                    }
                    chuqin.setCreateTime(d);
                    chuqin.setToday(date);
                    chuqin.setYonghuId(id);
                    chuqinService.insert(chuqin);
                }

            }else if("2".equals(flag)){
                //下班卡
                Date date1 = new Date();
                date1.setHours(19);
                date1.setMinutes(00);
                date1.setSeconds(0);
                Date date2 = new Date();
                date2.setHours(18);
                date2.setMinutes(00);
                date2.setSeconds(0);
                //下班打卡
                if(oldChuqinList!=null && oldChuqinList.size()>0){//不是第一次打卡
                    //查询当前用户是否生成了上班打卡
                    Wrapper<ChuqinEntity> queryWrapper = new EntityWrapper<ChuqinEntity>().eq("yonghu_id",id).eq("today",date).orderDesc(s);
                    ChuqinEntity chuqinEntity = chuqinService.selectOne(queryWrapper);
                    if(chuqinEntity !=null){//生成了上班打卡
                        chuqinEntity.setDownTime(d);
                        if("6".equals(String.valueOf(chuqinEntity.getChuqinTypes()))){
                        }else if(d.compareTo(date1)>0){//当前时间d 大于规定时间   加班
                            int hours = d.getHours();
                            int i = hours - 19+1;
                            if(i>0){
                                chuqinEntity.setOvertimeNumber(i);
                            }
                            chuqinEntity.setChuqinTypes(5);//设置为迟到
                        }else if(d.compareTo(date2)<0){//当前时间d 小于等于规定时间 早退
                            chuqinEntity.setChuqinTypes(7);//设置为未打下班卡
                        }else{
                            chuqinEntity.setChuqinTypes(1);
                        }
                        chuqinService.updateById(chuqinEntity);
                    }else{
                        //当天上午没有生成上班打卡,要防止用户昨天及之前没有生成打卡记录
                        Wrapper<ChuqinEntity> queryWrapper1 = new EntityWrapper<ChuqinEntity>().eq("yonghu_id",id).orderDesc(s);
                        List<ChuqinEntity> list = chuqinService.selectList(queryWrapper1);
                        if(list != null && list.size()>0){
                            ChuqinEntity entity = list.get(0);
                            String today = entity.getToday();//获取出勤表最大出勤
                            Date todayTime = format1.parse(today);
                            Calendar calendar = new GregorianCalendar();
                            calendar.setTime(todayTime);
                            calendar.add(calendar.DATE,1);
                            String newToday = format1.format(calendar.getTime());
                            if(date.equals(today)){
                                //昨天id+1  等于今天的话  就是直接新增下午打卡
                                ChuqinEntity chuqin = new ChuqinEntity();
                                chuqin.setDownTime(d);
                                chuqin.setChuqinTypes(2);
                                chuqin.setYonghuId(id);
                                chuqin.setToday(date);
                                chuqinService.insert(chuqin);
                            }else if(!date.equals(newToday)){//当天日期 不是出勤最大日期加一天的话   就是补充缺勤日期
                                chuqinList = this.getQueQin(d, format1, today, id);
                            }

                            if(chuqinList !=null && chuqinList.size() >0){
                                chuqinService.insertBatch(chuqinList);
                            }
                        }
                    }
                }else{
                    //第一次打卡
                    ChuqinEntity chuqin = new ChuqinEntity();
                    chuqin.setDownTime(d);
                    chuqin.setChuqinTypes(2);
                    chuqin.setYonghuId(id);
                    chuqin.setToday(date);
                    chuqinService.insert(chuqin);
                }
            }else {
                return R.error(511,"未知错误");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }
    /**
     * 获取缺勤列表.用于生成缺勤
     * @param d   当前日期
     * @param format1 "yyyy-MM-dd"
     * @param newToday 数据库存的最大打卡日期 加一天
     * @param id    打卡人id
     * @return
     * @throws ParseException
     */
    public static  List<ChuqinEntity> getQueQin(Date d,SimpleDateFormat format1,String newToday,Integer id) throws ParseException {
        List<ChuqinEntity> list = new ArrayList<>();
        // 返回的日期集合
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start = dateFormat.parse(newToday);
        Calendar   calendar = new GregorianCalendar();
        calendar.setTime(d);
        calendar.add(calendar.DATE,-1); //当前时间减去一天，即一天前的时间
        Date end = dateFormat.parse(format1.format(calendar.getTime()));

        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
        while (tempStart.before(tempEnd)) {
            ChuqinEntity chuqinEntity = new ChuqinEntity();
            chuqinEntity.setYonghuId(id);
            chuqinEntity.setToday(dateFormat.format(tempStart.getTime()));
            chuqinEntity.setCreateTime(d);
            chuqinEntity.setChuqinTypes(4);
            list.add(chuqinEntity);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return list;
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        chuqinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}

