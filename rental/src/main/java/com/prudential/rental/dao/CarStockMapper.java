package com.prudential.rental.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.prudential.rental.common.mybatis.Criteria;
import com.prudential.rental.model.entity.CarStock;

/**
 * @ClassName: CarStockMapper
 * @Description: CarStock表对应的dao操作Mapper映射类
 * @author: 
 */
@Mapper
public interface CarStockMapper {
	/**
	 * @Title CarStockMapper.countByExample
	 * @Description: 根据查询条件，计算car_stock个数
	 * @param example
	 *            通用查询条件类
	 * @return int 结果个数
	 */
	int countByExample(Criteria example);

	/**
	 * @Title CarStockMapper.deleteByExample
	 * @Description: 根据查询条件，删除CarStock
	 * @param example
	 *            通用查询条件类
	 * @return int 删除个数
	 */
	int deleteByExample(Criteria example);

	/**
	 * @Title CarStockMapper.deleteByPrimaryKey
	 * @Description: 根据属性名称，删除CarStock
	 * @param id
	 *            id
	 * @return int 删除个数
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * @Title CarStockMapper.insert
	 * @Description: 插入一个car_stock
	 * @param record
	 *            car_stock的bean对象
	 * @return int 插入个数
	 */
	int insert(CarStock record);

	/**
	 * @Title CarStockMapper.insertSelective
	 * @Description: 插入一个只有部分字段的car_stock
	 * @param record
	 *            只含部分字段的car_stock的bean对象
	 * @return int 插入个数
	 */
	int insertSelective(CarStock record);

	/**
	 * @Title CarStockMapper.selectByExample
	 * @Description: 根据查询条件类，返回car_stock结果集
	 * @param example
	 *            通用查询条件类
	 * @return List<CarStock>car_stock结果集
	 */
	List<CarStock> selectByExample(Criteria example);

	/**
	 * @Title CarStockMapper.selectByPrimaryKey
	 * @Description: 根据主键类，返回car_stock
	 * @param id
	 *            id
	 * @return CarStock bean对象
	 */
	CarStock selectByPrimaryKey(String id);

	/**
	 * @Title CarStockMapper.updateByExampleSelective
	 * @Description: 根据查询条件更新car_stock部分字段
	 * @param record
	 *            要更新成为的CarStock对象
	 * @param criteria
	 *            更新记录的查询条件
	 * @return int 更新记录数
	 */
	int updateByExampleSelective(@Param("record") CarStock record, @Param("example") Criteria criteria);

	/**
	 * @Title CarStockMapper.updateByExample
	 * @Description: 根据查询条件更新car_stock全表字段
	 * @param record
	 *            要更新成为的CarStock对象
	 * @param criteria
	 *            更新记录的查询条件
	 * @return int 更新记录数
	 */
	int updateByExample(@Param("record") CarStock record, @Param("example") Criteria criteria);

	/**
	 * @Title CarStockMapper.updateByPrimaryKeySelective
	 * @Description: 根据主键更新car_stock部分字段
	 * @param record
	 *            要更新成为的CarStock对象
	 * @return int 更新记录数
	 */
	int updateByPrimaryKeySelective(CarStock record);

	/**
	 * @Title CarStockMapper.updateByPrimaryKey
	 * @Description: 根据主键更新car_stock全部字段
	 * @param record
	 *            要更新成为的CarStock对象
	 * @return int 更新记录数
	 */
	int updateByPrimaryKey(CarStock record);
}