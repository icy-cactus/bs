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
 * 景点信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
@TableName("scenicinfo")
public class ScenicinfoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ScenicinfoEntity() {
		
	}
	
	public ScenicinfoEntity(T t) {
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
	 * 景点名称
	 */
					
	private String scenicname;
	
	/**
	 * 景点地址
	 */
					
	private String address;
	
	/**
	 * 景点等级
	 */
					
	private String sceniclevel;
	
	/**
	 * 门票价格
	 */
					
	private Integer price;
	
	/**
	 * 景点电话
	 */
					
	private String telephone;
	
	/**
	 * 景点介绍
	 */
					
	private String introduce;

	/**
	 * 简介
	 */

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 景点图片
	 */
					
	private String scenicphoto;
	
	/**
	 * 赞
	 */
					
	private Integer thumbsupnum;
	
	/**
	 * 踩
	 */
					
	private Integer crazilynum;
	
	/**
	 * 最近点击时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date clicktime;
	
	/**
	 * 点击次数
	 */
					
	private Integer clicknum;
	
	/**
	 * 评分
	 */

	private Double rating;
	/**
	 * 商家账号
	 */

	private String merchantusername;
	/**
	 * 商家名称
	 */

	private String merchantname;


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
	 * 设置：景点名称
	 */
	public void setScenicname(String scenicname) {
		this.scenicname = scenicname;
	}
	/**
	 * 获取：景点名称
	 */
	public String getScenicname() {
		return scenicname;
	}
	/**
	 * 设置：景点地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：景点地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：景点等级
	 */
	public void setSceniclevel(String sceniclevel) {
		this.sceniclevel = sceniclevel;
	}
	/**
	 * 获取：景点等级
	 */
	public String getSceniclevel() {
		return sceniclevel;
	}
	/**
	 * 设置：门票价格
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：门票价格
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：景点电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取：景点电话
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置：景点介绍
	 */
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	/**
	 * 获取：景点介绍
	 */
	public String getIntroduce() {
		return introduce;
	}
	/**
	 * 设置：景点图片
	 */
	public void setScenicphoto(String scenicphoto) {
		this.scenicphoto = scenicphoto;
	}
	/**
	 * 获取：景点图片
	 */
	public String getScenicphoto() {
		return scenicphoto;
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
	/**
	 * 设置：最近点击时间
	 */
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
	/**
	 * 设置：点击次数
	 */
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
	/**
	 * 设置：评分
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}
	/**
	 * 获取：评分
	 */
	public Double getRating() {
		return rating;
	}
	/**
	 * 设置：商家账号
	 */
	public void setMerchantusername(String merchantusername) {
		this.merchantusername = merchantusername;
	}
	/**
	 * 获取：商家账号
	 */
	public String getMerchantusername() {
		return merchantusername;
	}
	/**
	 * 设置：商家名称
	 */
	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}
	/**
	 * 获取：商家名称
	 */
	public String getMerchantname() {
		return merchantname;
	}

}
