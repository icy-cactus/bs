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


import com.dao.MerchantDao;
import com.entity.MerchantEntity;
import com.service.MerchantService;
import com.entity.view.MerchantView;

@Service("merchantService")
public class MerchantServiceImpl extends ServiceImpl<MerchantDao, MerchantEntity> implements MerchantService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MerchantEntity> page = this.selectPage(
                new Query<MerchantEntity>(params).getPage(),
                new EntityWrapper<MerchantEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<MerchantEntity> wrapper) {
		  Page<MerchantView> page =new Query<MerchantView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	
	@Override
	public List<MerchantView> selectListView(Wrapper<MerchantEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public MerchantView selectView(Wrapper<MerchantEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
