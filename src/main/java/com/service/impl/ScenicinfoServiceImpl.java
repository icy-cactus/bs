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


import com.dao.ScenicinfoDao;
import com.entity.ScenicinfoEntity;
import com.service.ScenicinfoService;
import com.entity.view.ScenicinfoView;

@Service("scenicinfoService")
public class ScenicinfoServiceImpl extends ServiceImpl<ScenicinfoDao, ScenicinfoEntity> implements ScenicinfoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ScenicinfoEntity> page = this.selectPage(
                new Query<ScenicinfoEntity>(params).getPage(),
                new EntityWrapper<ScenicinfoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ScenicinfoEntity> wrapper) {
		  Page<ScenicinfoView> page =new Query<ScenicinfoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    

	
	@Override
	public List<ScenicinfoView> selectListView(Wrapper<ScenicinfoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ScenicinfoView selectView(Wrapper<ScenicinfoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
