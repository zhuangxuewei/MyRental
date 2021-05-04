package com.prudential.rental.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.prudential.rental.common.mybatis.Criteria;
import com.prudential.rental.model.entity.RentalOrderDetail;

/**
 * @ClassName: RentalOrderDetailMapper
* @Description: rental_order_detail表对应的dao操作Mapper映射类
* @author: 
 */
@Mapper
public interface RentalOrderDetailMapper {
    /**
    * @Title RentalOrderDetailMapper.countByExample
    * @Description: 根据查询条件，计算rental_order_detail个数
    * @param example 通用查询条件类
    * @return int 结果个数
     */
    int countByExample(Criteria example);

    /**
    * @Title RentalOrderDetailMapper.deleteByExample
    * @Description: 根据查询条件，删除rental_order_detail
    * @param example 通用查询条件类
    * @return int  删除个数
     */
    int deleteByExample(Criteria example);

    /**
    * @Title RentalOrderDetailMapper.deleteByPrimaryKey
    * @Description: 根据属性名称，删除rental_order_detail
    * @param id id
    * @return int  删除个数
     */
    int deleteByPrimaryKey(String id);

    /**
    * @Title RentalOrderDetailMapper.insert
    * @Description: 插入一个rental_order_detail
    * @param record rental_order_detail的bean对象
    * @return int  插入个数
     */
    int insert(RentalOrderDetail record);

    /**
    * @Title RentalOrderDetailMapper.insertSelective
    * @Description: 插入一个只有部分字段的rental_order_detail
    * @param record 只含部分字段的rental_order_detail的bean对象
    * @return int  插入个数
     */
    int insertSelective(RentalOrderDetail record);

    /**
    * @Title RentalOrderDetailMapper.selectByExample
    * @Description: 根据查询条件类，返回rental_order_detail结果集
    * @param example 通用查询条件类
    * @return List<RentalOrderDetail>rental_order_detail结果集
     */
    List<RentalOrderDetail> selectByExample(Criteria example);

    /**
    * @Title RentalOrderDetailMapper.selectByPrimaryKey
    * @Description: 根据主键类，返回rental_order_detail
    * @param id id
    * @return RentalOrderDetail bean对象
     */
    RentalOrderDetail selectByPrimaryKey(String id);

    /**
    * @Title RentalOrderDetailMapper.updateByExampleSelective
    * @Description: 根据查询条件更新rental_order_detail部分字段
    * @param record 要更新成为的RentalOrderDetail对象
    * @param criteria 更新记录的查询条件
    * @return int 更新记录数
     */
    int updateByExampleSelective(@Param("record") RentalOrderDetail record, @Param("example") Criteria criteria);

    /**
    * @Title RentalOrderDetailMapper.updateByExample
    * @Description: 根据查询条件更新rental_order_detail全表字段
    * @param record 要更新成为的RentalOrderDetail对象
    * @param criteria 更新记录的查询条件
    * @return int 更新记录数
     */
    int updateByExample(@Param("record") RentalOrderDetail record, @Param("example") Criteria criteria);

    /**
    * @Title RentalOrderDetailMapper.updateByPrimaryKeySelective
    * @Description: 根据主键更新rental_order_detail部分字段
    * @param record 要更新成为的RentalOrderDetail对象
    * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(RentalOrderDetail record);

    /**
    * @Title RentalOrderDetailMapper.updateByPrimaryKey
    * @Description: 根据主键更新rental_order_detail全部字段
    * @param record 要更新成为的RentalOrderDetail对象
    * @return int 更新记录数
     */
    int updateByPrimaryKey(RentalOrderDetail record);
}