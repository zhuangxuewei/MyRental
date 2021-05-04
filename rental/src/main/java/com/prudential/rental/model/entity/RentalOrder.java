package com.prudential.rental.model.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.prudential.rental.model.enums.OrderStatus;

/**
 * 租赁订单
 * 
 * @author zhuangxuewei
 *
 */
public class RentalOrder {
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 用户id
	 */
	private String rentalUserId;
	/**
	 * 租赁时间
	 */
	private int rentalDays;
	/**
	 * 订单金额
	 */
	private BigDecimal totalAmount;
	/**
	 * 订单状态
	 */
	private OrderStatus orderStatus;

	/**
	 * 创建时间
	 */
	private Date dateCreated;
	/**
	 * 更新时间
	 */
	private Date dateUpdated;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getRentalUserId() {
		return rentalUserId;
	}

	public void setRentalUserId(String rentalUserId) {
		this.rentalUserId = rentalUserId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
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

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}
}
