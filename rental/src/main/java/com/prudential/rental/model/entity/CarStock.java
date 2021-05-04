package com.prudential.rental.model.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆库存
 * 
 * @author zhuangxuewei
 *
 */
public class CarStock extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 车型号
	 */
	private String carModel;
	/**
	 * 车辆库存
	 */
	private Integer stockNum;
	/**
	 * 车辆租赁价格
	 */
	private BigDecimal rentalPrice;
	/**
	 * 创建时间
	 */
	private Date dateCreated;
	/**
	 * 更新时间
	 */
	private Date dateUpdated;

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public BigDecimal getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(BigDecimal rentalPrice) {
		this.rentalPrice = rentalPrice;
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
