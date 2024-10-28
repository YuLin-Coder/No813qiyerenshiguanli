package com.entity.model;

import com.entity.CultivateEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 培训
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-12
 */
public class CultivateModel implements Serializable {
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
     * 培训开始时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date onTime;


    /**
     * 培训结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date downTime;


    /**
     * 培训天数
     */
    private Integer dayNumber;


    /**
     * 培训内容
     */
    private String cultivateContent;


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
	 * 获取：培训开始时间
	 */
    public Date getOnTime() {
        return onTime;
    }


    /**
	 * 设置：培训开始时间
	 */
    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }
    /**
	 * 获取：培训结束时间
	 */
    public Date getDownTime() {
        return downTime;
    }


    /**
	 * 设置：培训结束时间
	 */
    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }
    /**
	 * 获取：培训天数
	 */
    public Integer getDayNumber() {
        return dayNumber;
    }


    /**
	 * 设置：培训天数
	 */
    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }
    /**
	 * 获取：培训内容
	 */
    public String getCultivateContent() {
        return cultivateContent;
    }


    /**
	 * 设置：培训内容
	 */
    public void setCultivateContent(String cultivateContent) {
        this.cultivateContent = cultivateContent;
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
