package com.dao;

import com.entity.DiscusstrafficrouteEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.DiscusstrafficrouteView;


/**
 * 交通路线评论表
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
public interface DiscusstrafficrouteDao extends BaseMapper<DiscusstrafficrouteEntity> {
	

	
	List<DiscusstrafficrouteView> selectListView(@Param("ew") Wrapper<DiscusstrafficrouteEntity> wrapper);

	List<DiscusstrafficrouteView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusstrafficrouteEntity> wrapper);
	
	DiscusstrafficrouteView selectView(@Param("ew") Wrapper<DiscusstrafficrouteEntity> wrapper);
	

}
