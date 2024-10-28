package com.entity.model;

import com.entity.ChuqinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 考勤
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-12
 */
public class ChuqinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 打卡日期
     */
    private String today;


    /**
     * 上班打卡时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date onTime;


    /**
     * 下班打卡时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date downTime;


    /**
     * 打卡类型
     */
    private Integer chuqinTypes;


    /**
     * 加班时间(小时)
     */
    private Integer overtimeNumber;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：打卡日期
	 */
    public String getToday() {
        return today;
    }


    /**
	 * 设置：打卡日期
	 */
    public void setToday(String today) {
        this.today = today;
    }
    /**
	 * 获取：上班打卡时间
	 */
    public Date getOnTime() {
        return onTime;
    }


    /**
	 * 设置：上班打卡时间
	 */
    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }
    /**
	 * 获取：下班打卡时间
	 */
    public Date getDownTime() {
        return downTime;
    }


    /**
	 * 设置：下班打卡时间
	 */
    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }
    /**
	 * 获取：打卡类型
	 */
    public Integer getChuqinTypes() {
        return chuqinTypes;
    }


    /**
	 * 设置：打卡类型
	 */
    public void setChuqinTypes(Integer chuqinTypes) {
        this.chuqinTypes = chuqinTypes;
    }
    /**
	 * 获取：加班时间(小时)
	 */
    public Integer getOvertimeNumber() {
        return overtimeNumber;
    }


    /**
	 * 设置：加班时间(小时)
	 */
    public void setOvertimeNumber(Integer overtimeNumber) {
        this.overtimeNumber = overtimeNumber;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
