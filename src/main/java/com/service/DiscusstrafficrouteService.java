package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscusstrafficrouteEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscusstrafficrouteView;


/**
 * 交通路线评论表
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
public interface DiscusstrafficrouteService extends IService<DiscusstrafficrouteEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	
   	List<DiscusstrafficrouteView> selectListView(Wrapper<DiscusstrafficrouteEntity> wrapper);
   	
   	DiscusstrafficrouteView selectView(@Param("ew") Wrapper<DiscusstrafficrouteEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusstrafficrouteEntity> wrapper);
   	

}

