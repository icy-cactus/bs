package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ScenicinfoEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ScenicinfoView;


/**
 * 景点信息
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface ScenicinfoService extends IService<ScenicinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<ScenicinfoView> selectListView(Wrapper<ScenicinfoEntity> wrapper);
   	
   	ScenicinfoView selectView(@Param("ew") Wrapper<ScenicinfoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ScenicinfoEntity> wrapper);
   	

}

