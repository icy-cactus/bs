package com.dao;

import com.entity.DiscusstravelguideEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.DiscusstravelguideView;


/**
 * 旅游攻略评论表
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
public interface DiscusstravelguideDao extends BaseMapper<DiscusstravelguideEntity> {
	

	
	List<DiscusstravelguideView> selectListView(@Param("ew") Wrapper<DiscusstravelguideEntity> wrapper);

	List<DiscusstravelguideView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusstravelguideEntity> wrapper);
	
	DiscusstravelguideView selectView(@Param("ew") Wrapper<DiscusstravelguideEntity> wrapper);
	

}
