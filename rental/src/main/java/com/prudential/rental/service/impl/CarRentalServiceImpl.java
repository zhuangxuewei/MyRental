package com.prudential.rental.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prudential.rental.common.mybatis.Criteria;
import com.prudential.rental.common.util.RentalUtil;
import com.prudential.rental.dao.CarStockMapper;
import com.prudential.rental.dao.RentalOrderDetailMapper;
import com.prudential.rental.dao.RentalOrderMapper;
import com.prudential.rental.model.entity.BaseResult;
import com.prudential.rental.model.entity.CarStock;
import com.prudential.rental.model.entity.RentalOrder;
import com.prudential.rental.model.entity.RentalOrderDetail;
import com.prudential.rental.model.enums.GeneralResultTypeEnum;
import com.prudential.rental.model.enums.OrderStatus;
import com.prudential.rental.model.vo.CarOrderDetailVo;
import com.prudential.rental.model.vo.CarOrderSendBackVo;
import com.prudential.rental.model.vo.TakeCarOrderVo;
import com.prudential.rental.service.CarRentalService;

@Service
public class CarRentalServiceImpl implements CarRentalService {
	private static final Logger logger = LoggerFactory.getLogger(CarRentalServiceImpl.class);
	@Autowired
	private CarStockMapper carStockMapper;
	@Autowired
	private RentalOrderMapper rentalOrderMapper;
	@Autowired
	private RentalOrderDetailMapper rentalOrderDetailMapper;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public BaseResult takeRentalOrder(TakeCarOrderVo carOrderVo) throws Exception {
		String userId = carOrderVo.getUserId();
		if (StringUtils.isEmpty(userId)) {
			throw new Exception("获取不到用户信息，请重新登陆！");
		}
		List<CarOrderDetailVo> detailList = carOrderVo.getOrderDetailList();
		if (detailList == null || detailList.size() == 0) {
			throw new Exception("没有选择订单信息，请重新选择！");
		}
		BaseResult baseResult = new BaseResult();
		try {
			RentalOrder rentalOrder = new RentalOrder();
			rentalOrder.setRentalUserId(userId);
			String orderId = RentalUtil.generateOrderId();
			rentalOrder.setOrderId(orderId);
			rentalOrder.setOrderStatus(OrderStatus.UN_PAY);
			rentalOrder.setRentalDays(carOrderVo.getRentalDays());
			Date today = new Date();
			rentalOrder.setDateCreated(today);
			rentalOrder.setDateUpdated(today);

			BigDecimal totalAmount = new BigDecimal(0);

			for (CarOrderDetailVo carOrderDetailVo : detailList) {
				RentalOrderDetail orderDetail = new RentalOrderDetail();
				String carModel = carOrderDetailVo.getCarModel();
				Criteria carStockexample = new Criteria();
				carStockexample.createConditon().andEqualTo("car_model", carModel);
				List<CarStock> carStockList = carStockMapper.selectByExample(carStockexample);
				if (carStockList == null || carStockList.size() == 0) {
					throw new Exception("没有车型为" + carModel + "的车辆，请重新选择！");
				}
				CarStock carStock = carStockList.get(0);
				int stockNum = carStock.getStockNum();
				int rentalNum = carOrderDetailVo.getRentalNum();
				// 减库存
				int currentStockNum = stockNum - rentalNum;
				if (currentStockNum < 0) {
					throw new Exception("车型为" + carModel + "的车辆库存不足，请重新选择！");
				}
				carStock.setStockNum(currentStockNum);
				carStock.setDateUpdated(today);
				int flag = carStockMapper.updateByExample(carStock, carStockexample);
				if (flag != 1) {
					throw new Exception(carModel + "减库存失败！");
				}
				// 再次查询，防止超卖，抛出异常回滚
				CarStock carStockNew = carStockMapper.selectByPrimaryKey(carStock.getId());
				int newStockNum = carStockNew.getStockNum();
				if (newStockNum < 0) {
					throw new Exception("车型为" + carModel + "的车辆库存不足，请重新选择！");
				}
				orderDetail.setCarStockId(carStock.getId());
				orderDetail.setOrderId(orderId);
				orderDetail.setRentalCarNum(rentalNum);
				orderDetail.setDateCreated(today);
				orderDetail.setDateUpdated(today);
				// 生成订单详情
				flag = rentalOrderDetailMapper.insert(orderDetail);
				if (flag != 1) {
					throw new Exception("生成订单详情失败！carModel=" + carModel);
				}
				// 计算订单总金额
				totalAmount = totalAmount.add(carStock.getRentalPrice().multiply(new BigDecimal(rentalNum)));
			}

			rentalOrder.setTotalAmount(totalAmount);
			// 生成订单
			int flag = rentalOrderMapper.insert(rentalOrder);
			if (flag != 1) {
				throw new Exception("生成订单失败！orderId=" + orderId);
			}

		} catch (Exception e) {
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg("租赁下单异常！");
			logger.error("租赁下单异常！", e);
		}
		return baseResult;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public BaseResult rentalSendBack(String orderId) throws Exception {
		BaseResult baseResult = new BaseResult();
		if (StringUtils.isEmpty(orderId)) {
			baseResult.setResultCode(GeneralResultTypeEnum.FAIL.getCode());
			baseResult.setResultMsg("没有订单信息，请重新选择");
			return baseResult;
		}

		try {
			Criteria carStockexample = new Criteria();
			carStockexample.createConditon().andEqualTo("order_id", orderId);
			List<RentalOrder> orderList = rentalOrderMapper.selectByExample(carStockexample);
			if (orderList == null || orderList.size() == 0) {
				baseResult.setResultCode(GeneralResultTypeEnum.FAIL.getCode());
				baseResult.setResultMsg("没有订单信息，请重新选择");
				return baseResult;
			}
			RentalOrder rentalOrder = orderList.get(0);
			if (!canSendBack(rentalOrder)) {
				baseResult.setResultCode(GeneralResultTypeEnum.FAIL.getCode());
				baseResult.setResultMsg("该订单状态不允许该操作");
				return baseResult;
			}
			rentalOrder.setOrderStatus(OrderStatus.SEND_BACK);
			Date today = new Date();
			rentalOrder.setDateUpdated(today);
			// 更新订单表
			rentalOrderMapper.updateByPrimaryKey(rentalOrder);
			List<RentalOrderDetail> orderDetailList = rentalOrderDetailMapper.selectByExample(carStockexample);
			if (orderDetailList == null || orderDetailList.size() == 0) {
				throw new Exception("没有订单详情信息，请重新选择！");
			}
			for (RentalOrderDetail orderDetail : orderDetailList) {
				CarStock carStock = carStockMapper.selectByPrimaryKey(orderDetail.getCarStockId());
				if (carStock == null) {
					logger.error("找不到对应的车辆库存记录！");
					continue;
				}
				int currentStockNum = carStock.getStockNum() + orderDetail.getRentalCarNum();
				// 加库存
				if (currentStockNum < 0) {
					logger.error("加库存出错！currentStockNum", currentStockNum);
					continue;
				}
				carStock.setStockNum(currentStockNum);
				carStock.setDateUpdated(today);
				int flag = carStockMapper.updateByPrimaryKey(carStock);
				if (flag != 1) {
					throw new Exception(carStock.getCarModel() + "加库存失败！");
				}
			}
		} catch (Exception e) {
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg("租赁归还异常！");
			logger.error("租赁归还异常！", e);
		}
		return baseResult;
	}

	private boolean canSendBack(RentalOrder rentalOrder) {
		OrderStatus status = rentalOrder.getOrderStatus();
		if (status == null || status.equals(OrderStatus.CANCEL) || status.equals(OrderStatus.SEND_BACK)) {
			return false;
		}
		return true;
	}

	@Override
	public BaseResult listAllRentalOrder(String userId) throws Exception {
		if (StringUtils.isEmpty(userId)) {
			throw new Exception("获取不到用户信息，请重新登陆！");
		}
		BaseResult baseResult = new BaseResult();
		try {
			Criteria carStockexample = new Criteria();
			carStockexample.createConditon().andEqualTo("rental_user_id", userId);
			List<RentalOrder> orderList = rentalOrderMapper.selectByExample(carStockexample);
			if (orderList == null || orderList.size() == 0) {
				throw new Exception("没有订单信息，请重新选择！");
			}
			baseResult.setDatas(orderList);
		} catch (Exception e) {
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg("获取订单列表异常！");
			logger.error("获取订单列表异常！", e);
		}
		return baseResult;
	}

	@Override
	public BaseResult getRentalOrderDetail(String orderId) throws Exception {
		if (StringUtils.isEmpty(orderId)) {
			throw new Exception("没有订单信息，请重新选择！");
		}
		BaseResult baseResult = new BaseResult();
		try {
			Criteria orderExample = new Criteria();
			orderExample.createConditon().andEqualTo("order_id", orderId);
			List<RentalOrderDetail> orderDetailList = rentalOrderDetailMapper.selectByExample(orderExample);
			baseResult.setDatas(orderDetailList);
		} catch (Exception e) {
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg("获取订单详情异常！");
			logger.error("获取订单详情异常！", e);
		}
		return baseResult;
	}

}
