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


import com.dao.DiscusstravelguideDao;
import com.entity.DiscusstravelguideEntity;
import com.service.DiscusstravelguideService;
import com.entity.view.DiscusstravelguideView;

@Service("discusstravelguideService")
public class DiscusstravelguideServiceImpl extends ServiceImpl<DiscusstravelguideDao, DiscusstravelguideEntity> implements DiscusstravelguideService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusstravelguideEntity> page = this.selectPage(
                new Query<DiscusstravelguideEntity>(params).getPage(),
                new EntityWrapper<DiscusstravelguideEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusstravelguideEntity> wrapper) {
		  Page<DiscusstravelguideView> page =new Query<DiscusstravelguideView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	
	@Override
	public List<DiscusstravelguideView> selectListView(Wrapper<DiscusstravelguideEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusstravelguideView selectView(Wrapper<DiscusstravelguideEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
