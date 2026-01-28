package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TravelguideEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TravelguideView;


/**
 * 旅游攻略
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface TravelguideService extends IService<TravelguideEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	List<TravelguideView> selectListView(Wrapper<TravelguideEntity> wrapper);
   	
   	TravelguideView selectView(@Param("ew") Wrapper<TravelguideEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TravelguideEntity> wrapper);
   	

}

