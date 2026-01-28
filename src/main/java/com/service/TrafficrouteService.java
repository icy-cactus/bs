package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TrafficrouteEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TrafficrouteView;


/**
 * 交通路线
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface TrafficrouteService extends IService<TrafficrouteEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	
   	List<TrafficrouteView> selectListView(Wrapper<TrafficrouteEntity> wrapper);
   	
   	TrafficrouteView selectView(@Param("ew") Wrapper<TrafficrouteEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TrafficrouteEntity> wrapper);
   	

}

