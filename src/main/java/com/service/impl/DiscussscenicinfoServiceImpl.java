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


import com.dao.DiscussscenicinfoDao;
import com.entity.DiscussscenicinfoEntity;
import com.service.DiscussscenicinfoService;

import com.entity.view.DiscussscenicinfoView;

@Service("discussscenicinfoService")
public class DiscussscenicinfoServiceImpl extends ServiceImpl<DiscussscenicinfoDao, DiscussscenicinfoEntity> implements DiscussscenicinfoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussscenicinfoEntity> page = this.selectPage(
                new Query<DiscussscenicinfoEntity>(params).getPage(),
                new EntityWrapper<DiscussscenicinfoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussscenicinfoEntity> wrapper) {
		  Page<DiscussscenicinfoView> page =new Query<DiscussscenicinfoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	
	@Override
	public List<DiscussscenicinfoView> selectListView(Wrapper<DiscussscenicinfoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussscenicinfoView selectView(Wrapper<DiscussscenicinfoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
