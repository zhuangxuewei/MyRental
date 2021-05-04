package com.prudential.rental.service;

import com.prudential.rental.model.entity.BaseResult;
import com.prudential.rental.model.vo.CarOrderSendBackVo;
import com.prudential.rental.model.vo.TakeCarOrderVo;
/**
 * 
 * @ClassName: CarRentalService
 * @Description: 汽车租赁服务
 * @author zhuangxuewei
 * @date 2021年5月4日
 *
 */
public interface CarRentalService {
	/**
	 * 
	 * @Title: takeRentalOrder
	 * @Description: 租赁下单
	 * @param @param carOrderVo
	 * @param @return
	 * @param @throws Exception 参数
	 * @return BaseResult 返回类型
	 * @throws
	 */
	public BaseResult takeRentalOrder(TakeCarOrderVo carOrderVo) throws Exception;
	/**
	 * 
	 * @Title: rentalSendBack
	 * @Description: 租赁归还
	 * @param @param carOrderSendBackVo
	 * @param @return
	 * @param @throws Exception 参数
	 * @return BaseResult 返回类型
	 * @throws
	 */
	public BaseResult rentalSendBack(CarOrderSendBackVo carOrderSendBackVo) throws Exception;
	/**
	 * 
	 * @Title: listAllRentalOrder
	 * @Description:  列出该用户所有订单
	 * @param @param userId
	 * @param @return
	 * @param @throws Exception 参数
	 * @return BaseResult 返回类型
	 * @throws
	 */
	public BaseResult listAllRentalOrder(String userId) throws Exception;
	/**
	 * 
	 * @Title: getRentalOrderDetail
	 * @Description: 获取订单详情
	 * @param @param orderId
	 * @param @return
	 * @param @throws Exception 参数
	 * @return BaseResult 返回类型
	 * @throws
	 */
	public BaseResult getRentalOrderDetail(String orderId) throws Exception;
	
}