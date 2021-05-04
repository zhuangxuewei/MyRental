package com.prudential.rental.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prudential.rental.common.mybatis.Criteria;
import com.prudential.rental.dao.CarStockMapper;
import com.prudential.rental.model.entity.BaseResult;
import com.prudential.rental.model.entity.CarStock;
import com.prudential.rental.model.enums.GeneralResultTypeEnum;
import com.prudential.rental.service.CarStockService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
@SuppressWarnings("unchecked")
public class CarStockServiceImplTest {
	@Autowired
	private CarStockService carStockService;

	@Autowired
	private CarStockMapper carStockMapper;

	@Test
	public void testListAllCarStock() throws Exception {
		BaseResult baseResult = carStockService.listAllCarStock();
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());
		Object obj = baseResult.getDatas();
		Assert.assertNotNull(obj);
		boolean isTypeTrue = obj instanceof List;
		Assert.assertTrue(isTypeTrue);
		List<CarStock> carStockList = (List<CarStock>) baseResult.getDatas();
		Assert.assertTrue(carStockList.size() > 0);
	}

	@Test
	public void testAddCarStock() throws Exception {
		CarStock carStock = new CarStock();
		carStock.setCarModel("BENZ");
		carStock.setStockNum(4);
		carStock.setRentalPrice(new BigDecimal(200));
		carStock.setDateCreated(new Date());
		carStock.setDateUpdated(new Date());
		BaseResult baseResult = carStockService.addCarStock(carStock);
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());
		Criteria carStockexample = new Criteria();
		carStockexample.createConditon().andEqualTo("car_model", "BENZ");
		List<CarStock> carStockList = carStockMapper.selectByExample(carStockexample);
		Assert.assertNotNull(carStockList);
		Assert.assertTrue(carStockList.size() > 0);

	}

	@Test
	public void testupdateCarStock() throws Exception {

		Criteria carStockexample = new Criteria();
		carStockexample.createConditon().andEqualTo("car_model", "BENZ");
		List<CarStock> carStockList = carStockMapper.selectByExample(carStockexample);
		Assert.assertNotNull(carStockList);
		Assert.assertTrue(carStockList.size() > 0);
		CarStock newCarStock = carStockList.get(0);
		newCarStock.setStockNum(10);
		newCarStock.setRentalPrice(new BigDecimal(300));
		newCarStock.setDateUpdated(new Date());
		BaseResult baseResult = carStockService.updateCarStock(newCarStock);
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());

		carStockList = carStockMapper.selectByExample(carStockexample);
		Assert.assertNotNull(carStockList);
		Assert.assertTrue(carStockList.size() > 0);
		newCarStock = carStockList.get(0);
		Assert.assertTrue(newCarStock.getStockNum() == 10);
		Assert.assertTrue(newCarStock.getRentalPrice().intValue() == 300);
	}

	@Test
	public void testDeleteCarStock() throws Exception {
		BaseResult baseResult = carStockService.listAllCarStock();
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());
		Object obj = baseResult.getDatas();
		Assert.assertNotNull(obj);
		boolean isTypeTrue = obj instanceof List;
		Assert.assertTrue(isTypeTrue);
		List<CarStock> carStockList = (List<CarStock>) baseResult.getDatas();
		Assert.assertTrue(carStockList.size() > 0);
		String deleteId = "";
		for (CarStock carStock : carStockList) {
			if (carStock.getCarModel().equals("BENZ")) {
				deleteId = carStock.getId();
				break;
			}
		}
		Assert.assertNotNull(deleteId);
		baseResult = carStockService.deleteCarStock(deleteId);
		Assert.assertTrue(baseResult.getResultCode() == GeneralResultTypeEnum.SUCCESS.getCode());
		CarStock carStock = carStockMapper.selectByPrimaryKey(deleteId);
		Assert.assertNull(carStock);
	}

}
