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


import com.dao.TravelguideDao;
import com.entity.TravelguideEntity;
import com.service.TravelguideService;
import com.entity.view.TravelguideView;

@Service("travelguideService")
public class TravelguideServiceImpl extends ServiceImpl<TravelguideDao, TravelguideEntity> implements TravelguideService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TravelguideEntity> page = this.selectPage(
                new Query<TravelguideEntity>(params).getPage(),
                new EntityWrapper<TravelguideEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TravelguideEntity> wrapper) {
		  Page<TravelguideView> page =new Query<TravelguideView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	
	@Override
	public List<TravelguideView> selectListView(Wrapper<TravelguideEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TravelguideView selectView(Wrapper<TravelguideEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
