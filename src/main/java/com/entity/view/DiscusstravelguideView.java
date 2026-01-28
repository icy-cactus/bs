package com.entity.view;

import com.entity.DiscusstravelguideEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 旅游攻略评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
@TableName("discusstravelguide")
public class DiscusstravelguideView  extends DiscusstravelguideEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscusstravelguideView(){
	}
 
 	public DiscusstravelguideView(DiscusstravelguideEntity discusstravelguideEntity){
 	try {
			BeanUtils.copyProperties(this, discusstravelguideEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
