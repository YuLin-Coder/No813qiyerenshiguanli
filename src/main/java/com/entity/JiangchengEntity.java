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
 * 奖惩
 *
 * @author 
 * @email
 * @date 2021-04-12
 */
@TableName("jiangcheng")
public class JiangchengEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiangchengEntity() {

	}

	public JiangchengEntity(T t) {
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
     * 用户id
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 奖惩名称
     */
    @TableField(value = "jiangcheng_name")

    private String jiangchengName;


    /**
     * 奖惩类型
     */
    @TableField(value = "jiangcheng_types")

    private Integer jiangchengTypes;


    /**
     * 奖惩详情
     */
    @TableField(value = "jiangcheng_content")

    private String jiangchengContent;


    /**
     * 奖惩时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 设置：用户id
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户id
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：奖惩名称
	 */
    public String getJiangchengName() {
        return jiangchengName;
    }


    /**
	 * 获取：奖惩名称
	 */

    public void setJiangchengName(String jiangchengName) {
        this.jiangchengName = jiangchengName;
    }
    /**
	 * 设置：奖惩类型
	 */
    public Integer getJiangchengTypes() {
        return jiangchengTypes;
    }


    /**
	 * 获取：奖惩类型
	 */

    public void setJiangchengTypes(Integer jiangchengTypes) {
        this.jiangchengTypes = jiangchengTypes;
    }
    /**
	 * 设置：奖惩详情
	 */
    public String getJiangchengContent() {
        return jiangchengContent;
    }


    /**
	 * 获取：奖惩详情
	 */

    public void setJiangchengContent(String jiangchengContent) {
        this.jiangchengContent = jiangchengContent;
    }
    /**
	 * 设置：奖惩时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：奖惩时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Jiangcheng{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", jiangchengName=" + jiangchengName +
            ", jiangchengTypes=" + jiangchengTypes +
            ", jiangchengContent=" + jiangchengContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
