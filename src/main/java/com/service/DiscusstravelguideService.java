package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusstravelguideEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusstravelguideView;


/**
 * 旅游攻略评论表
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
public interface DiscusstravelguideService extends IService<DiscusstravelguideEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	
   	List<DiscusstravelguideView> selectListView(Wrapper<DiscusstravelguideEntity> wrapper);
   	
   	DiscusstravelguideView selectView(@Param("ew") Wrapper<DiscusstravelguideEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusstravelguideEntity> wrapper);
   	

}

