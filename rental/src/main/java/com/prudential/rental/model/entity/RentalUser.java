package com.prudential.rental.model.entity;

import java.util.Date;

/**
 * 用户
 * 
 * @author zhuangxuewei
 *
 */
public class RentalUser extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账号
	 */
	private String userId;
	/**
	 * 职业
	 */
	private String career;
	/**
	 * 支付卡号
	 */
	private String payCardNo;
	/**
	 * 创建时间
	 */
	private Date dateCreated;
	/**
	 * 更新时间
	 */
	private Date dateUpdated;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getPayCardNo() {
		return payCardNo;
	}

	public void setPayCardNo(String payCardNo) {
		this.payCardNo = payCardNo;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

}
