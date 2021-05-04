package com.prudential.rental.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prudential.rental.dao.RentalOrderMapper;
import com.prudential.rental.model.entity.BaseResult;
import com.prudential.rental.model.entity.RentalOrder;
import com.prudential.rental.model.entity.RentalOrderDetail;
import com.prudential.rental.model.enums.GeneralResultTypeEnum;
import com.prudential.rental.model.enums.OrderStatus;
import com.prudential.rental.model.vo.CarOrderDetailVo;
import com.prudential.rental.model.vo.CarOrderSendBackVo;
import com.prudential.rental.model.vo.TakeCarOrderVo;
import com.prudential.rental.service.CarRentalService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
public class CarRentalServiceImplTest {
	@Autowired
	private CarRentalService carRentalService;

	@Autowired
	private RentalOrderMapper rentalOrderMapper;

	@Test
	public void testTakeRentalOrder() throws Exception {
		TakeCarOrderVo takeCarOrderVo = new TakeCarOrderVo();
		takeCarOrderVo.setUserId("user1");
		takeCarOrderVo.setRentalDays(2);

		List<CarOrderDetailVo> detailList = new ArrayList<>();
		CarOrderDetailVo carOrderDetailVo = new CarOrderDetailVo();
		carOrderDetailVo.setCarModel("Toyota Camry");
		carOrderDetailVo.setRentalNum(2);
		detailList.add(carOrderDetailVo);

		CarOrderDetailVo carOrderDetailVo1 = new CarOrderDetailVo();
		carOrderDetailVo1.setCarModel("BMW 650");
		carOrderDetailVo1.setRentalNum(2);
		detailList.add(carOrderDetailVo1);
		takeCarOrderVo.setOrderDetailList(detailList);
		carRentalService.takeRentalOrder(takeCarOrderVo);
		BaseResult baseResult = carRentalService.listAllRentalOrder("user1");
		Assert.assertNotNull(baseResult.getDatas());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRentalSendBack() throws Exception {
		BaseResult baseResult = carRentalService.listAllRentalOrder("user1");
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());

		List<RentalOrder> orderList = (List<RentalOrder>) baseResult.getDatas();
		Assert.assertTrue(orderList != null && orderList.size() > 0);
		RentalOrder rentalOrder=null;
		for (RentalOrder element : orderList) {
			if(element.getOrderStatus().equals(OrderStatus.UN_PAY)){
				rentalOrder=element;
				break;
			}
		}
		Assert.assertNotNull(rentalOrder);
		
		CarOrderSendBackVo carOrderSendBackVo = new CarOrderSendBackVo();
		carOrderSendBackVo.setUserId("user1");
		carOrderSendBackVo.setOrderId(rentalOrder.getOrderId());
		baseResult = carRentalService.rentalSendBack(carOrderSendBackVo);
		
		RentalOrder newOrder = rentalOrderMapper.selectByPrimaryKey(rentalOrder.getId());
		Assert.assertTrue(newOrder.getOrderStatus() == OrderStatus.SEND_BACK);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testListAllRentalOrder() throws Exception {
		BaseResult baseResult = carRentalService.listAllRentalOrder("user1");
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());

		Object obj = baseResult.getDatas();
		Assert.assertNotNull(obj);
		boolean isTypeTrue = obj instanceof List;
		Assert.assertTrue(isTypeTrue);
		List<RentalOrder> orderList = (List<RentalOrder>) baseResult.getDatas();
		Assert.assertTrue(orderList.size() > 0);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetRentalOrderDetail() throws Exception {
		BaseResult baseResult = carRentalService.listAllRentalOrder("user1");
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());

		List<RentalOrder> orderList = (List<RentalOrder>) baseResult.getDatas();
		Assert.assertTrue(orderList != null && orderList.size() > 0);

		RentalOrder rentalOrder = orderList.get(0);
		baseResult = carRentalService.getRentalOrderDetail(rentalOrder.getOrderId());
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());

		Object obj = baseResult.getDatas();
		Assert.assertNotNull(obj);
		boolean isTypeTrue = obj instanceof List;
		Assert.assertTrue(isTypeTrue);
		List<RentalOrderDetail> orderDetailList = (List<RentalOrderDetail>) baseResult.getDatas();
		Assert.assertTrue(orderDetailList.size() > 0);
	}

}
