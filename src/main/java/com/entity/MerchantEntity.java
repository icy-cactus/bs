package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 商家
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 
 */
@TableName("merchant")
public class MerchantEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public MerchantEntity() {
		
	}
	
	public MerchantEntity(T t) {
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
	 * 商家账号
	 */
					
	private String merchantusername;

	/**
	 * 密码
	 */
					
	private String password;
	
	/**
	 * 商家名称
	 */
					
	private String merchantname;
	
	/**
	 * 商家地址
	 */
					
	private String merchantaddress;
	
	/**
	 * 邮箱
	 */
					
	private String email;
	
	/**
	 * 联系手机
	 */
					
	private String phonenumber;
	
	/**
	 * 营业执照
	 */
					
	private String merchantlicense;
	

	
	
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
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
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
	/**
	 * 设置：商家地址
	 */
	public void setMerchantaddress(String merchantaddress) {
		this.merchantaddress = merchantaddress;
	}
	/**
	 * 获取：商家地址
	 */
	public String getMerchantaddress() {
		return merchantaddress;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：联系手机
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	/**
	 * 获取：联系手机
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	/**
	 * 设置：营业执照
	 */
	public void setMerchantlicense(String merchantlicense) {
		this.merchantlicense = merchantlicense;
	}
	/**
	 * 获取：营业执照
	 */
	public String getMerchantlicense() {
		return merchantlicense;
	}


}
