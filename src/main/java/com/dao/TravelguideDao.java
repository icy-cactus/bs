package com.dao;

import com.entity.TravelguideEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TravelguideView;


/**
 * 旅游攻略
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface TravelguideDao extends BaseMapper<TravelguideEntity> {
	

	
	List<TravelguideView> selectListView(@Param("ew") Wrapper<TravelguideEntity> wrapper);

	List<TravelguideView> selectListView(Pagination page,@Param("ew") Wrapper<TravelguideEntity> wrapper);
	
	TravelguideView selectView(@Param("ew") Wrapper<TravelguideEntity> wrapper);
	

}
