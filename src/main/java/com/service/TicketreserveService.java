package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TicketreserveEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TicketreserveView;


/**
 * 门票预订
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface TicketreserveService extends IService<TicketreserveEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	
   	List<TicketreserveView> selectListView(Wrapper<TicketreserveEntity> wrapper);
   	
   	TicketreserveView selectView(@Param("ew") Wrapper<TicketreserveEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TicketreserveEntity> wrapper);

	Double calculateRating(Long id);
}

