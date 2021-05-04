package com.prudential.rental.service;

import com.prudential.rental.model.entity.BaseResult;
import com.prudential.rental.model.entity.CarStock;
/**
 * 
 * @ClassName: CarStockService
 * @Description: 汽车租赁库存服务
 * @author zhuangxuewei
 * @date 2021年5月4日
 *
 */
public interface CarStockService {
	/**
	 * 
	 * @Title: listAllCarStock
	 * @Description: 列出所有租赁库存
	 * @param @param carOrderVo
	 * @param @return
	 * @param @throws Exception 参数
	 * @return BaseResult 返回类型
	 * @throws
	 */
	public BaseResult listAllCarStock() throws Exception;
	/**
	 * 
	 * @Title: deleteCarStock
	 * @Description: 删除库存
	 * @param @param id
	 * @param @return
	 * @param @throws Exception 参数
	 * @return BaseResult 返回类型
	 * @throws
	 */
	public BaseResult deleteCarStock(String id) throws Exception;
	/**
	 * 
	 * @Title: addCarStock
	 * @Description: 添加库存
	 * @param @param carStock
	 * @param @return
	 * @param @throws Exception 参数
	 * @return BaseResult 返回类型
	 * @throws
	 */
	public BaseResult addCarStock(CarStock carStock) throws Exception;
	
	
	
}