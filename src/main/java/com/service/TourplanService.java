package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TourplanEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TourplanView;


/**
 * 旅游规划
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
public interface TourplanService extends IService<TourplanEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	
   	List<TourplanView> selectListView(Wrapper<TourplanEntity> wrapper);
   	
   	TourplanView selectView(@Param("ew") Wrapper<TourplanEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TourplanEntity> wrapper);
   	

}

