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


import com.dao.TrafficrouteDao;
import com.entity.TrafficrouteEntity;
import com.service.TrafficrouteService;
import com.entity.view.TrafficrouteView;

@Service("trafficrouteService")
public class TrafficrouteServiceImpl extends ServiceImpl<TrafficrouteDao, TrafficrouteEntity> implements TrafficrouteService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TrafficrouteEntity> page = this.selectPage(
                new Query<TrafficrouteEntity>(params).getPage(),
                new EntityWrapper<TrafficrouteEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TrafficrouteEntity> wrapper) {
		  Page<TrafficrouteView> page =new Query<TrafficrouteView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	
	@Override
	public List<TrafficrouteView> selectListView(Wrapper<TrafficrouteEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TrafficrouteView selectView(Wrapper<TrafficrouteEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
