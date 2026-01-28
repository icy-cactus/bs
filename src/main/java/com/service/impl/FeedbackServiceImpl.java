package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.FeedbackDao;
import com.entity.FeedbackEntity;
import com.service.FeedbackService;

import com.entity.view.FeedbackView;

@Service("feedbackService")
public class FeedbackServiceImpl extends ServiceImpl<FeedbackDao, FeedbackEntity> implements FeedbackService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FeedbackEntity> page = this.selectPage(
                new Query<FeedbackEntity>(params).getPage(),
                new EntityWrapper<FeedbackEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<FeedbackEntity> wrapper) {
		  Page<FeedbackView> page =new Query<FeedbackView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	
	@Override
	public List<FeedbackView> selectListView(Wrapper<FeedbackEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public FeedbackView selectView(Wrapper<FeedbackEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
