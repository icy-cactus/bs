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
 * 门票预订
 * 数据库通用操作实体类（普通增删改查）
 *
 * @author
 * @email
 * @date 2025-11-09 18:31:55
 */
@TableName("ticketreserve")
public class TicketreserveEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public TicketreserveEntity() {

    }

    public TicketreserveEntity(T t) {
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
     * 门票价格
     */

    private Float price;

    /**
     * 景点等级
     */

    private String sceniclevel;

    /**
     * 预订时间
     */

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date reservetime;

    /**
     * 备注
     */

    private String remark;

    /**
     * 账号
     */

    private String username;

    /**
     * 姓名
     */

    private String name;

    /**
     * 手机
     */

    private String telephone;

    /**
     * 是否支付
     */

    private String ispay;


    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;
    private Long refid;
    private Float rating;
    private String review;
    private String sfsh;
    private String shhf;
    private String avatar;
    /**
     * 商家账号
     */

    private String merchantusername;
    /**
     * 商家名称
     */

    private String merchantname;

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
     * 设置：门票价格
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取：门票价格
     */
    public Float getPrice() {
        return price;
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
     * 设置：预订时间
     */
    public void setReservetime(Date reservetime) {
        this.reservetime = reservetime;
    }

    /**
     * 获取：预订时间
     */
    public Date getReservetime() {
        return reservetime;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
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
     * 设置：手机
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取：手机
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置：是否支付
     */
    public void setIspay(String ispay) {
        this.ispay = ispay;
    }

    /**
     * 获取：是否支付
     */
    public String getIspay() {
        return ispay;
    }
    /**
     * 设置：评分
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }
    /**
     * 获取：评分
     */
    public Float getRating() {
        return rating;
    }
    /**
     * 设置：评论
     */
    public void setReview(String review) {
        this.review = review;
    }
    /**
     * 获取：评论
     */
    public String getReview() {
        return review;
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
    /**
     * 设置：用户头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    /**
     * 获取：用户头像
     */
    public String getAvatar() {
        return avatar;
    }

    public Long getRefid() {
        return refid;
    }

    public void setRefid(Long refid) {
        this.refid = refid;
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
