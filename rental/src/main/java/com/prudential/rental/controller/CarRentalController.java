package com.prudential.rental.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prudential.rental.model.entity.BaseResult;
import com.prudential.rental.model.enums.GeneralResultTypeEnum;
import com.prudential.rental.model.vo.CarOrderSendBackVo;
import com.prudential.rental.model.vo.TakeCarOrderVo;
import com.prudential.rental.service.CarRentalService;

/**
 * 
 * @ClassName: CarRentalController
 * @Description: 车辆租赁控制器
 * @author zhuangxuewei
 * @date 2021年5月4日
 *
 */
@RestController
@RequestMapping(value = "/rental")
public class CarRentalController {
	private static final Logger logger = LoggerFactory.getLogger(CarRentalController.class);
	@Autowired
	private CarRentalService carRentalService;

	/**
	 * 
	 * @Title: takeRentalOrder @Description: 租赁下单方法
	 *         创建订单，订单详情列表，减库存 @param @param carOrderVo @param @return
	 *         参数 @return BaseResult 返回类型 @throws
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResult takeRentalOrder(@RequestBody TakeCarOrderVo carOrderVo) {
		BaseResult baseResult = new BaseResult();
		try {
			baseResult = carRentalService.takeRentalOrder(carOrderVo);
		} catch (Exception e) {
			logger.error("租赁下单失败：" + e.getMessage(), e);
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg(e.getMessage());
		}
		return baseResult;
	}

	/**
	 * 
	 * @Title: rentalSendBack @Description: 租赁归还方法，修改订单状态，加库存 @param @param
	 *         carOrderSendBackVo @param @param orderId @param @return
	 *         参数 @return BaseResult 返回类型 @throws
	 */
	@RequestMapping(value = "/orders/{orderId}/sendBack", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public BaseResult rentalSendBack( @PathVariable String orderId) {
		BaseResult baseResult = new BaseResult();
		try {
			baseResult = carRentalService.rentalSendBack(orderId);
		} catch (Exception e) {
			logger.error("租赁归还失败：" + e.getMessage(), e);
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg(e.getMessage());
		}
		return baseResult;
	}

	/**
	 * 
	 * @Title: listAllRentalOrder @Description: 列出用户所有订单 @param @param
	 *         userId @param @return 参数 @return BaseResult 返回类型 @throws
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public BaseResult listAllRentalOrder(@RequestParam("userId") String userId) {
		BaseResult baseResult = new BaseResult();
		try {
			baseResult = carRentalService.listAllRentalOrder(userId);
		} catch (Exception e) {
			logger.error("获取订单列表失败：" + e.getMessage(), e);
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg(e.getMessage());
		}
		return baseResult;
	}

	@RequestMapping(value = "/orders/{orderId}/detail", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public BaseResult getRentalOrderDetail(@PathVariable String orderId) {
		BaseResult baseResult = new BaseResult();
		try {
			baseResult = carRentalService.getRentalOrderDetail(orderId);
		} catch (Exception e) {
			logger.error("获取订单详情失败：" + e.getMessage(), e);
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg(e.getMessage());
		}
		return baseResult;
	}
}
