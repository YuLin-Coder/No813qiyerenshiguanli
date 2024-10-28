package com.dao;

import com.entity.JiangchengEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiangchengView;

/**
 * 奖惩 Dao 接口
 *
 * @author 
 * @since 2021-04-12
 */
public interface JiangchengDao extends BaseMapper<JiangchengEntity> {

   List<JiangchengView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
