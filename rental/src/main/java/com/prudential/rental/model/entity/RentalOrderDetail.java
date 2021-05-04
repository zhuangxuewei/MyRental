package com.prudential.rental.model.entity;

import java.util.Date;

/**
 * 订单详情
 * 
 * @author zhuangxuewei
 *
 */
public class RentalOrderDetail extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 车辆库存id
	 */
	private String carStockId;
	/**
	 * 租借车辆数量
	 */
	private int rentalCarNum;
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

	public String getCarStockId() {
		return carStockId;
	}

	public void setCarStockId(String carStockId) {
		this.carStockId = carStockId;
	}

	public int getRentalCarNum() {
		return rentalCarNum;
	}

	public void setRentalCarNum(int rentalCarNum) {
		this.rentalCarNum = rentalCarNum;
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
