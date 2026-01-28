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


import com.dao.DiscusstrafficrouteDao;
import com.entity.DiscusstrafficrouteEntity;
import com.service.DiscusstrafficrouteService;

import com.entity.view.DiscusstrafficrouteView;

@Service("discusstrafficrouteService")
public class DiscusstrafficrouteServiceImpl extends ServiceImpl<DiscusstrafficrouteDao, DiscusstrafficrouteEntity> implements DiscusstrafficrouteService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusstrafficrouteEntity> page = this.selectPage(
                new Query<DiscusstrafficrouteEntity>(params).getPage(),
                new EntityWrapper<DiscusstrafficrouteEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusstrafficrouteEntity> wrapper) {
		  Page<DiscusstrafficrouteView> page =new Query<DiscusstrafficrouteView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	
	@Override
	public List<DiscusstrafficrouteView> selectListView(Wrapper<DiscusstrafficrouteEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusstrafficrouteView selectView(Wrapper<DiscusstrafficrouteEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
