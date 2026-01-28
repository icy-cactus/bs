package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;


/**
 * 交通路线
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
@TableName("trafficroute")
public class TrafficrouteEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TrafficrouteEntity() {
		
	}
	
	public TrafficrouteEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 路线名称
	 */
					
	private String trafficname;
	
	/**
	 * 路线类型
	 */
					
	private String traffictype;
	
	/**
	 * 始发地
	 */
					
	private String origin;
	
	/**
	 * 终点地
	 */
					
	private String destination;
	
	/**
	 * 全程距离
	 */
					
	private String distance;
	
	/**
	 * 路线详情
	 */
					
	private String trafficcontent;
	
	/**
	 * 路线图片
	 */
					
	private String trafficphoto;

	/**
	 * 路线简介
	 */

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：路线名称
	 */
	public void setTrafficname(String trafficname) {
		this.trafficname = trafficname;
	}
	/**
	 * 获取：路线名称
	 */
	public String getTrafficname() {
		return trafficname;
	}
	/**
	 * 设置：路线类型
	 */
	public void setTraffictype(String traffictype) {
		this.traffictype = traffictype;
	}
	/**
	 * 获取：路线类型
	 */
	public String getTraffictype() {
		return traffictype;
	}
	/**
	 * 设置：始发地
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * 获取：始发地
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * 设置：终点地
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * 获取：终点地
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * 设置：全程距离
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}
	/**
	 * 获取：全程距离
	 */
	public String getDistance() {
		return distance;
	}
	/**
	 * 设置：路线详情
	 */
	public void setTrafficcontent(String trafficcontent) {
		this.trafficcontent = trafficcontent;
	}
	/**
	 * 获取：路线详情
	 */
	public String getTrafficcontent() {
		return trafficcontent;
	}
	/**
	 * 设置：路线图片
	 */
	public void setTrafficphoto(String trafficphoto) {
		this.trafficphoto = trafficphoto;
	}
	/**
	 * 获取：路线图片
	 */
	public String getTrafficphoto() {
		return trafficphoto;
	}

}
