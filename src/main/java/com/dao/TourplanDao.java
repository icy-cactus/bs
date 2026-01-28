package com.dao;

import com.entity.TourplanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.TourplanView;


/**
 * 旅游规划
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
public interface TourplanDao extends BaseMapper<TourplanEntity> {
	

	
	List<TourplanView> selectListView(@Param("ew") Wrapper<TourplanEntity> wrapper);

	List<TourplanView> selectListView(Pagination page,@Param("ew") Wrapper<TourplanEntity> wrapper);
	
	TourplanView selectView(@Param("ew") Wrapper<TourplanEntity> wrapper);
	

}
