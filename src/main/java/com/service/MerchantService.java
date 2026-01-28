package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.MerchantEntity;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.entity.view.MerchantView;


/**
 * 商家
 *
 * @author 
 * @email 
 * @date 
 */
public interface MerchantService extends IService<MerchantEntity> {

    PageUtils queryPage(Map<String, Object> params);
    

   	
   	List<MerchantView> selectListView(Wrapper<MerchantEntity> wrapper);
   	
   	MerchantView selectView(@Param("ew") Wrapper<MerchantEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<MerchantEntity> wrapper);
   	

}

