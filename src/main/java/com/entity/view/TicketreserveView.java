package com.entity.view;

import com.entity.TicketreserveEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 门票预订
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
@TableName("ticketreserve")
public class TicketreserveView  extends TicketreserveEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public TicketreserveView(){
	}
 
 	public TicketreserveView(TicketreserveEntity ticketreserveEntity){
 	try {
			BeanUtils.copyProperties(this, ticketreserveEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
