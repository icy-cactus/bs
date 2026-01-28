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


import com.dao.TourplanDao;
import com.entity.TourplanEntity;
import com.service.TourplanService;
import com.entity.view.TourplanView;

@Service("tourplanService")
public class TourplanServiceImpl extends ServiceImpl<TourplanDao, TourplanEntity> implements TourplanService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TourplanEntity> page = this.selectPage(
                new Query<TourplanEntity>(params).getPage(),
                new EntityWrapper<TourplanEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TourplanEntity> wrapper) {
		  Page<TourplanView> page =new Query<TourplanView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	
	@Override
	public List<TourplanView> selectListView(Wrapper<TourplanEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TourplanView selectView(Wrapper<TourplanEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
