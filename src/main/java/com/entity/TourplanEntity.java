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
 * 旅游规划
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
@TableName("tourplan")
public class TourplanEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TourplanEntity() {
		
	}
	
	public TourplanEntity(T t) {
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
	 * 目的地
	 */
					
	private String mudedi;
	
	/**
	 * 出发时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date starttime;
	
	/**
	 * 预计天数
	 */
					
	private Integer duration;
	
	/**
	 * 旅游路线
	 */
					
	private String route;
	
	/**
	 * 规划内容
	 */
					
	private String content;
	
	/**
	 * 账号
	 */
					
	private String username;
	
	/**
	 * 姓名
	 */
					
	private String name;
	
	
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
	 * 设置：目的地
	 */
	public void setMudedi(String mudedi) {
		this.mudedi = mudedi;
	}
	/**
	 * 获取：目的地
	 */
	public String getMudedi() {
		return mudedi;
	}
	/**
	 * 设置：出发时间
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	/**
	 * 获取：出发时间
	 */
	public Date getStarttime() {
		return starttime;
	}
	/**
	 * 设置：预计天数
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	/**
	 * 获取：预计天数
	 */
	public Integer getDuration() {
		return duration;
	}
	/**
	 * 设置：旅游路线
	 */
	public void setRoute(String route) {
		this.route = route;
	}
	/**
	 * 获取：旅游路线
	 */
	public String getRoute() {
		return route;
	}
	/**
	 * 设置：规划内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：规划内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：账号
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：账号
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}

}
