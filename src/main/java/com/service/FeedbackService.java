package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.FeedbackEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.FeedbackView;


/**
 * 反馈意见
 *
 * @author 
 * @email 
 * @date 2025-11-28 00:27:23
 */
public interface FeedbackService extends IService<FeedbackEntity> {

    PageUtils queryPage(Map<String, Object> params);

   	List<FeedbackView> selectListView(Wrapper<FeedbackEntity> wrapper);
   	
   	FeedbackView selectView(@Param("ew") Wrapper<FeedbackEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FeedbackEntity> wrapper);
   	

}

