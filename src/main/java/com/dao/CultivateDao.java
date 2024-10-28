package com.dao;

import com.entity.CultivateEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CultivateView;

/**
 * 培训 Dao 接口
 *
 * @author 
 * @since 2021-04-12
 */
public interface CultivateDao extends BaseMapper<CultivateEntity> {

   List<CultivateView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
