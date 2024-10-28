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
 * 考勤
 *
 * @author 
 * @email
 * @date 2021-04-12
 */
@TableName("chuqin")
public class ChuqinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ChuqinEntity() {

	}

	public ChuqinEntity(T t) {
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
     * 打卡日期
     */
    @TableField(value = "today")

    private String today;


    /**
     * 上班打卡时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "on_time",fill = FieldFill.UPDATE)

    private Date onTime;


    /**
     * 下班打卡时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "down_time",fill = FieldFill.UPDATE)

    private Date downTime;


    /**
     * 打卡类型
     */
    @TableField(value = "chuqin_types")

    private Integer chuqinTypes;


    /**
     * 加班时间(小时)
     */
    @TableField(value = "overtimeNumber")

    private Integer overtimeNumber;


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
	 * 设置：打卡日期
	 */
    public String getToday() {
        return today;
    }


    /**
	 * 获取：打卡日期
	 */

    public void setToday(String today) {
        this.today = today;
    }
    /**
	 * 设置：上班打卡时间
	 */
    public Date getOnTime() {
        return onTime;
    }


    /**
	 * 获取：上班打卡时间
	 */

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }
    /**
	 * 设置：下班打卡时间
	 */
    public Date getDownTime() {
        return downTime;
    }


    /**
	 * 获取：下班打卡时间
	 */

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }
    /**
	 * 设置：打卡类型
	 */
    public Integer getChuqinTypes() {
        return chuqinTypes;
    }


    /**
	 * 获取：打卡类型
	 */

    public void setChuqinTypes(Integer chuqinTypes) {
        this.chuqinTypes = chuqinTypes;
    }
    /**
	 * 设置：加班时间(小时)
	 */
    public Integer getOvertimeNumber() {
        return overtimeNumber;
    }


    /**
	 * 获取：加班时间(小时)
	 */

    public void setOvertimeNumber(Integer overtimeNumber) {
        this.overtimeNumber = overtimeNumber;
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
        return "Chuqin{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", today=" + today +
            ", onTime=" + onTime +
            ", downTime=" + downTime +
            ", chuqinTypes=" + chuqinTypes +
            ", overtimeNumber=" + overtimeNumber +
            ", createTime=" + createTime +
        "}";
    }
}
