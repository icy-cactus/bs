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
 * 旅游攻略
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
@TableName("travelguide")
public class TravelguideEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TravelguideEntity() {
		
	}
	
	public TravelguideEntity(T t) {
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
	 * 标题
	 */

	private String title;
	/**
	 * 旅行地点
	 */
					
	private String address;
	
	/**
	 * 攻略类型
	 */
					
	private String traveltype;
	
	/**
	 * 旅行天数
	 */
					
	private Integer duration;
	
	/**
	 * 旅行内容
	 */
					
	private String introduction;
	
	/**
	 * 旅行图片
	 */
					
	private String travelphoto;
	
	/**
	 * 账号
	 */
					
	private String username;
	
	/**
	 * 姓名
	 */
					
	private String name;
	
	/**
	 * 赞
	 */
					
	private Integer thumbsupnum;
	
	/**
	 * 踩
	 */
					
	private Integer crazilynum;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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
	 * 设置：旅行地点
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：旅行地点
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：攻略类型
	 */
	public void setTraveltype(String traveltype) {
		this.traveltype = traveltype;
	}
	/**
	 * 获取：攻略类型
	 */
	public String getTraveltype() {
		return traveltype;
	}
	/**
	 * 设置：旅行天数
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	/**
	 * 获取：旅行天数
	 */
	public Integer getDuration() {
		return duration;
	}
	/**
	 * 设置：旅行内容
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * 获取：旅行内容
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * 设置：旅行图片
	 */
	public void setTravelphoto(String travelphoto) {
		this.travelphoto = travelphoto;
	}
	/**
	 * 获取：旅行图片
	 */
	public String getTravelphoto() {
		return travelphoto;
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
	/**
	 * 设置：赞
	 */
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
	/**
	 * 设置：踩
	 */
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}

}
