package com.dao;

import com.entity.UsersEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.UsersView;


/**
 * 用户
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface UsersDao extends BaseMapper<UsersEntity> {
	

	
	List<UsersView> selectListView(@Param("ew") Wrapper<UsersEntity> wrapper);

	List<UsersView> selectListView(Pagination page,@Param("ew") Wrapper<UsersEntity> wrapper);
	
	UsersView selectView(@Param("ew") Wrapper<UsersEntity> wrapper);
	

}
