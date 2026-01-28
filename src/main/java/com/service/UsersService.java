package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.UsersEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.UsersView;


/**
 * 用户
 *
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
public interface UsersService extends IService<UsersEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	
   	List<UsersView> selectListView(Wrapper<UsersEntity> wrapper);
   	
   	UsersView selectView(@Param("ew") Wrapper<UsersEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<UsersEntity> wrapper);
   	

}

