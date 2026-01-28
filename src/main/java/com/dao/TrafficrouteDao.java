package com.dao;

import com.entity.TrafficrouteEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.TrafficrouteView;


/**
 * 交通路线
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface TrafficrouteDao extends BaseMapper<TrafficrouteEntity> {

	
	List<TrafficrouteView> selectListView(@Param("ew") Wrapper<TrafficrouteEntity> wrapper);

	List<TrafficrouteView> selectListView(Pagination page,@Param("ew") Wrapper<TrafficrouteEntity> wrapper);
	
	TrafficrouteView selectView(@Param("ew") Wrapper<TrafficrouteEntity> wrapper);
	

}
