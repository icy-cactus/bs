package com.dao;

import com.entity.MerchantEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import com.entity.view.MerchantView;


/**
 * 商家
 * 
 * @author 
 * @email 
 * @date 
 */
public interface MerchantDao extends BaseMapper<MerchantEntity> {
	

	
	List<MerchantView> selectListView(@Param("ew") Wrapper<MerchantEntity> wrapper);

	List<MerchantView> selectListView(Pagination page,@Param("ew") Wrapper<MerchantEntity> wrapper);
	
	MerchantView selectView(@Param("ew") Wrapper<MerchantEntity> wrapper);
	

}
