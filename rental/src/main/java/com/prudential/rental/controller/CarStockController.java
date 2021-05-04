package com.prudential.rental.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.prudential.rental.model.entity.BaseResult;
import com.prudential.rental.model.entity.CarStock;
import com.prudential.rental.model.enums.GeneralResultTypeEnum;
import com.prudential.rental.service.CarStockService;

@RestController
@RequestMapping(value = "/rental")
public class CarStockController {
	private static final Logger logger = LoggerFactory.getLogger(CarStockController.class);
	@Resource
	CarStockService carStockService;

	/**
	 * 
	 * @Title: listAllCarStock @Description: 列出所有库存 @param @param
	 * carOrderVo @param @return 参数 @return BaseResult 返回类型 @throws
	 */
	@RequestMapping(value = "/carStocks", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public BaseResult listAllCarStock() {
		BaseResult baseResult = new BaseResult();
		try {
			baseResult = carStockService.listAllCarStock();
		} catch (Exception e) {
			logger.error("列出车辆库存失败：" + e.getMessage(), e);
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg(e.getMessage());
		}
		return baseResult;
	}

	/**
	 * 
	 * @Title: addCarStock @Description: 添加车辆库存 @param @param
	 * carStock @param @return 参数 @return BaseResult 返回类型 @throws
	 */
	@RequestMapping(value = "/carStocks", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public BaseResult addCarStock(@RequestBody CarStock carStock) {
		BaseResult baseResult = new BaseResult();
		try {
			baseResult = carStockService.addCarStock(carStock);
		} catch (Exception e) {
			logger.error("添加车辆库存失败：" + e.getMessage(), e);
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg(e.getMessage());
		}
		return baseResult;
	}

	/**
	 * 
	 * @Title: deleteCarStock @Description: 删除某个车辆库存 @param @param
	 * id @param @return 参数 @return BaseResult 返回类型 @throws
	 */
	@RequestMapping(value = "/carStocks/{Id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	public BaseResult deleteCarStock(@PathVariable String id) {
		BaseResult baseResult = new BaseResult();
		try {
			baseResult = carStockService.deleteCarStock(id);
		} catch (Exception e) {
			logger.error("删除车辆库存失败：" + e.getMessage(), e);
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg(e.getMessage());
		}
		return baseResult;
	}

	@RequestMapping(value = "/carStocks/{Id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	public BaseResult updateCarStock(@PathVariable String id, CarStock carStock) {
		BaseResult baseResult = new BaseResult();
		try {
			baseResult = carStockService.updateCarStock(carStock);
		} catch (Exception e) {
			logger.error("更新车辆库存失败：" + e.getMessage(), e);
			baseResult = new BaseResult();
			baseResult.setResultCode(GeneralResultTypeEnum.EXCEPTION.getCode());
			baseResult.setResultMsg(e.getMessage());
		}
		return baseResult;
	}
}
