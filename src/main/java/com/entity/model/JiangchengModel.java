package com.entity.model;

import com.entity.JiangchengEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 奖惩
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-12
 */
public class JiangchengModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户id
     */
    private Integer yonghuId;


    /**
     * 奖惩名称
     */
    private String jiangchengName;


    /**
     * 奖惩类型
     */
    private Integer jiangchengTypes;


    /**
     * 奖惩详情
     */
    private String jiangchengContent;


    /**
     * 奖惩时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
	 * 获取：用户id
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户id
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：奖惩名称
	 */
    public String getJiangchengName() {
        return jiangchengName;
    }


    /**
	 * 设置：奖惩名称
	 */
    public void setJiangchengName(String jiangchengName) {
        this.jiangchengName = jiangchengName;
    }
    /**
	 * 获取：奖惩类型
	 */
    public Integer getJiangchengTypes() {
        return jiangchengTypes;
    }


    /**
	 * 设置：奖惩类型
	 */
    public void setJiangchengTypes(Integer jiangchengTypes) {
        this.jiangchengTypes = jiangchengTypes;
    }
    /**
	 * 获取：奖惩详情
	 */
    public String getJiangchengContent() {
        return jiangchengContent;
    }


    /**
	 * 设置：奖惩详情
	 */
    public void setJiangchengContent(String jiangchengContent) {
        this.jiangchengContent = jiangchengContent;
    }
    /**
	 * 获取：奖惩时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：奖惩时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
