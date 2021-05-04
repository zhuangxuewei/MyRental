package com.prudential.rental.model.vo;

import java.util.List;

/**
 * 
 * @ClassName: CarOrderSendBackVo
 * @Description: 租约到期，用户归还车辆请求vo
 * @author zhuangxuewei
 * @date 2021年5月4日
 *
 */
public class CarOrderSendBackVo {
	/**
	 * 用户账号
	 */
	private String userId;
	/**
	 * 租赁订单id
	 */
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
