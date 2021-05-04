package com.prudential.rental.model.vo;

/**
 *   车辆租赁订单详情请求vo
 * 
 * @author zhuangxuewei
 *
 */
public class CarOrderDetailVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 车辆model
	 */
	private String carModel;
	/**
	 * 租赁数量
	 */
	private int rentalNum;

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public int getRentalNum() {
		return rentalNum;
	}

	public void setRentalNum(int rentalNum) {
		this.rentalNum = rentalNum;
	}

}
