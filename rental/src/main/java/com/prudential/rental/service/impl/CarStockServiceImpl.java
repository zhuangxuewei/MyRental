package com.prudential.rental.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prudential.rental.common.mybatis.Criteria;
import com.prudential.rental.dao.CarStockMapper;
import com.prudential.rental.model.entity.BaseResult;
import com.prudential.rental.model.entity.CarStock;
import com.prudential.rental.model.enums.GeneralResultTypeEnum;
import com.prudential.rental.service.CarStockService;

@Service
public class CarStockServiceImpl implements CarStockService {
	private static final Logger logger = LoggerFactory.getLogger(CarRentalServiceImpl.class);
	@Autowired
	private CarStockMapper carStockMapper;

	@Override
	public BaseResult listAllCarStock() throws Exception {
		BaseResult baseResult = new BaseResult();
		try {
			Criteria carStockexample = new Criteria();
			List<CarStock> carStockList = carStockMapper.selectByExample(carStockexample);
			baseResult.setDatas(carStockList);
		} catch (Exception e) {
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg("租赁归还异常！");
			logger.error("租赁归还异常！", e);
		}
		return baseResult;
	}

	@Override
	public BaseResult deleteCarStock(String id) throws Exception {
		BaseResult baseResult = new BaseResult();
		try {
			int flag = carStockMapper.deleteByPrimaryKey(id);
			if (flag != 1) {
				throw new Exception("删除库存失败！");
			}
		} catch (Exception e) {
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg("删除库存异常！");
			logger.error("删除库存异常！", e);
		}
		return baseResult;
	}

	@Override
	public BaseResult addCarStock(CarStock carStock) throws Exception {
		BaseResult baseResult = new BaseResult();
		try {
			carStock.setDateCreated(new Date());
			carStock.setDateUpdated(new Date());
			int flag = carStockMapper.insert(carStock);
			if (flag != 1) {
				throw new Exception("添加库存失败！");
			}
		} catch (Exception e) {
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg("添加库存异常！");
			logger.error("添加库存异常！", e);
		}
		return baseResult;
	}

	@Override
	public BaseResult updateCarStock(CarStock carStock) throws Exception {
		BaseResult baseResult = new BaseResult();
		try {
			carStock.setDateUpdated(new Date());
			int flag = carStockMapper.updateByPrimaryKeySelective(carStock);
			if (flag != 1) {
				throw new Exception("更新库存失败！");
			}
		} catch (Exception e) {
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg("更新库存异常！");
			logger.error("更新库存异常！", e);
		}
		return baseResult;
	}

}
