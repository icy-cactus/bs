package com.dao;

import com.entity.ScenicinfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.ScenicinfoView;


/**
 * 景点信息
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface ScenicinfoDao extends BaseMapper<ScenicinfoEntity> {

	
	List<ScenicinfoView> selectListView(@Param("ew") Wrapper<ScenicinfoEntity> wrapper);

	List<ScenicinfoView> selectListView(Pagination page,@Param("ew") Wrapper<ScenicinfoEntity> wrapper);
	
	ScenicinfoView selectView(@Param("ew") Wrapper<ScenicinfoEntity> wrapper);
	

}
