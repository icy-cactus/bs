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


import com.dao.TicketreserveDao;
import com.entity.TicketreserveEntity;
import com.service.TicketreserveService;
import com.entity.view.TicketreserveView;

@Service("ticketreserveService")
public class TicketreserveServiceImpl extends ServiceImpl<TicketreserveDao, TicketreserveEntity> implements TicketreserveService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TicketreserveEntity> page = this.selectPage(
                new Query<TicketreserveEntity>(params).getPage(),
                new EntityWrapper<TicketreserveEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TicketreserveEntity> wrapper) {
		  Page<TicketreserveView> page =new Query<TicketreserveView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    

	@Override
	public List<TicketreserveView> selectListView(Wrapper<TicketreserveEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TicketreserveView selectView(Wrapper<TicketreserveEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

	@Override
	public Double calculateRating(Long id) {
		return baseMapper.calculateRating(id);
	}
}
