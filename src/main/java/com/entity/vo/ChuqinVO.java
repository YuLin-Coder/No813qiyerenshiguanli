package com.entity.vo;

import com.entity.ChuqinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 考勤
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-04-12
 */
@TableName("chuqin")
public class ChuqinVO implements Serializable {
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
     * 打卡日期
     */

    @TableField(value = "today")
    private String today;


    /**
     * 上班打卡时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "on_time")
    private Date onTime;


    /**
     * 下班打卡时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "down_time")
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

}
