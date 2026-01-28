package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussscenicinfoEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussscenicinfoView;


/**
 * 景点信息评论表
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
public interface DiscussscenicinfoService extends IService<DiscussscenicinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	List<DiscussscenicinfoView> selectListView(Wrapper<DiscussscenicinfoEntity> wrapper);
   	
   	DiscussscenicinfoView selectView(@Param("ew") Wrapper<DiscussscenicinfoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussscenicinfoEntity> wrapper);
   	

}

