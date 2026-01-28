package com.dao;

import com.entity.DiscussscenicinfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.DiscussscenicinfoView;


/**
 * 景点信息评论表
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
public interface DiscussscenicinfoDao extends BaseMapper<DiscussscenicinfoEntity> {
	

	
	List<DiscussscenicinfoView> selectListView(@Param("ew") Wrapper<DiscussscenicinfoEntity> wrapper);

	List<DiscussscenicinfoView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussscenicinfoEntity> wrapper);
	
	DiscussscenicinfoView selectView(@Param("ew") Wrapper<DiscussscenicinfoEntity> wrapper);
	

}
