package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 培训
 *
 * @author 
 * @email
 * @date 2021-03-02
 */
@TableName("cultivate")
public class CultivateEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CultivateEntity() {

	}

	public CultivateEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "on_time",fill = FieldFill.UPDATE)

    private Date onTime;


    /**
     * 培训结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "down_time",fill = FieldFill.UPDATE)

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
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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

    @Override
    public String toString() {
        return "Cultivate{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", onTime=" + onTime +
            ", downTime=" + downTime +
            ", dayNumber=" + dayNumber +
            ", cultivateContent=" + cultivateContent +
            ", createTime=" + createTime +
        "}";
    }
}
