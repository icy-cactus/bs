package com.entity.view;

import com.entity.TravelguideEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 旅游攻略
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
@TableName("travelguide")
public class TravelguideView  extends TravelguideEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public TravelguideView(){
	}
 
 	public TravelguideView(TravelguideEntity travelguideEntity){
 	try {
			BeanUtils.copyProperties(this, travelguideEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
