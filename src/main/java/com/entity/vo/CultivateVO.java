package com.entity.vo;

import com.entity.CultivateEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 培训
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-04-12
 */
@TableName("cultivate")
public class CultivateVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 培训开始时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "on_time")
    private Date onTime;


    /**
     * 培训结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "down_time")
    private Date downTime;


    /**
     * 培训天数
     */

    @TableField(value = "day_number")
    private Integer dayNumber;


    /**
     * 培训内容
     */

    @TableField(value = "cultivate_content")
    private String cultivateContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：培训开始时间
	 */
    public Date getOnTime() {
        return onTime;
    }


    /**
	 * 获取：培训开始时间
	 */

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }
    /**
	 * 设置：培训结束时间
	 */
    public Date getDownTime() {
        return downTime;
    }


    /**
	 * 获取：培训结束时间
	 */

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }
    /**
	 * 设置：培训天数
	 */
    public Integer getDayNumber() {
        return dayNumber;
    }


    /**
	 * 获取：培训天数
	 */

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }
    /**
	 * 设置：培训内容
	 */
    public String getCultivateContent() {
        return cultivateContent;
    }


    /**
	 * 获取：培训内容
	 */

    public void setCultivateContent(String cultivateContent) {
        this.cultivateContent = cultivateContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
