package com.dao;

import com.entity.TicketreserveEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.TicketreserveView;


/**
 * 门票预订
 * 
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface TicketreserveDao extends BaseMapper<TicketreserveEntity> {
	

	
	List<TicketreserveView> selectListView(@Param("ew") Wrapper<TicketreserveEntity> wrapper);

	List<TicketreserveView> selectListView(Pagination page,@Param("ew") Wrapper<TicketreserveEntity> wrapper);
	
	TicketreserveView selectView(@Param("ew") Wrapper<TicketreserveEntity> wrapper);

	Double calculateRating(Long id);

}
