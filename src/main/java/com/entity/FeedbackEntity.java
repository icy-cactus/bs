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
 * 反馈意见
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-11-28 00:27:23
 */
@TableName("feedback")
public class FeedbackEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public FeedbackEntity() {
		
	}
	
	public FeedbackEntity(T t) {
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
	 * 反馈图片
	 */
					
	private String picture;
	
	/**
	 * 反馈名称
	 */
					
	private String title;
	
	/**
	 * 反馈时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date feedbacktime;
	
	/**
	 * 反馈内容
	 */
					
	private String content;
	
	/**
	 * 用户账号
	 */
					
	private String userid;
	
	/**
	 * 用户姓名
	 */
					
	private String username;
	
	/**
	 * 备注
	 */
					
	private String tips;
	
	/**
	 * 是否审核
	 */
					
	private String sfsh;
	
	/**
	 * 审核回复
	 */
					
	private String shhf;
	
	
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
	 * 设置：反馈图片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取：反馈图片
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * 设置：反馈名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：反馈名称
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：反馈时间
	 */
	public void setFeedbacktime(Date feedbacktime) {
		this.feedbacktime = feedbacktime;
	}
	/**
	 * 获取：反馈时间
	 */
	public Date getFeedbacktime() {
		return feedbacktime;
	}
	/**
	 * 设置：反馈内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：反馈内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：用户账号
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 获取：用户账号
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：备注
	 */
	public void setTips(String tips) {
		this.tips = tips;
	}
	/**
	 * 获取：备注
	 */
	public String getTips() {
		return tips;
	}
	/**
	 * 设置：是否审核
	 */
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
	/**
	 * 设置：审核回复
	 */
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}

}
