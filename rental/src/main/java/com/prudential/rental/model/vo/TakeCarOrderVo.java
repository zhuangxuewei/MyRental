package com.prudential.rental.model.vo;

import java.util.List;

/**
 *   车辆租赁订单请求vo
 * 
 * @author zhuangxuewei
 *
 */
public class TakeCarOrderVo {
	/**
	 * 用户账号
	 */
	private String userId;
	/**
	 * 租赁时间
	 */
	private int rentalDays;
	/**
	 * 订单详情list
	 */
	private List<CarOrderDetailVo> orderDetailList;

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<CarOrderDetailVo> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<CarOrderDetailVo> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

}
