package com.prudential.rental.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.prudential.rental.common.mybatis.Criteria;
import com.prudential.rental.model.entity.RentalOrder;

/**
 * @ClassName: RentalOrderMapper
* @Description: rental_order表对应的dao操作Mapper映射类
* @author: 
 */
@Mapper
public interface RentalOrderMapper {
    /**
    * @Title RentalOrderMapper.countByExample
    * @Description: 根据查询条件，计算rental_order个数
    * @param example 通用查询条件类
    * @return int 结果个数
     */
    int countByExample(Criteria example);

    /**
    * @Title RentalOrderMapper.deleteByExample
    * @Description: 根据查询条件，删除rental_order
    * @param example 通用查询条件类
    * @return int  删除个数
     */
    int deleteByExample(Criteria example);

    /**
    * @Title RentalOrderMapper.deleteByPrimaryKey
    * @Description: 根据属性名称，删除rental_order
    * @param id id
    * @return int  删除个数
     */
    int deleteByPrimaryKey(String id);

    /**
    * @Title RentalOrderMapper.insert
    * @Description: 插入一个rental_order
    * @param record rental_order的bean对象
    * @return int  插入个数
     */
    int insert(RentalOrder record);

    /**
    * @Title RentalOrderMapper.insertSelective
    * @Description: 插入一个只有部分字段的rental_order
    * @param record 只含部分字段的rental_order的bean对象
    * @return int  插入个数
     */
    int insertSelective(RentalOrder record);

    /**
    * @Title RentalOrderMapper.selectByExample
    * @Description: 根据查询条件类，返回rental_order结果集
    * @param example 通用查询条件类
    * @return List<RentalOrder>rental_order结果集
     */
    List<RentalOrder> selectByExample(Criteria example);

    /**
    * @Title RentalOrderMapper.selectByPrimaryKey
    * @Description: 根据主键类，返回rental_order
    * @param id id
    * @return RentalOrder bean对象
     */
    RentalOrder selectByPrimaryKey(String id);

    /**
    * @Title RentalOrderMapper.updateByExampleSelective
    * @Description: 根据查询条件更新rental_order部分字段
    * @param record 要更新成为的RentalOrder对象
    * @param criteria 更新记录的查询条件
    * @return int 更新记录数
     */
    int updateByExampleSelective(@Param("record") RentalOrder record, @Param("example") Criteria criteria);

    /**
    * @Title RentalOrderMapper.updateByExample
    * @Description: 根据查询条件更新rental_order全表字段
    * @param record 要更新成为的RentalOrder对象
    * @param criteria 更新记录的查询条件
    * @return int 更新记录数
     */
    int updateByExample(@Param("record") RentalOrder record, @Param("example") Criteria criteria);

    /**
    * @Title RentalOrderMapper.updateByPrimaryKeySelective
    * @Description: 根据主键更新rental_order部分字段
    * @param record 要更新成为的RentalOrder对象
    * @return int 更新记录数
     */
    int updateByPrimaryKeySelective(RentalOrder record);

    /**
    * @Title RentalOrderMapper.updateByPrimaryKey
    * @Description: 根据主键更新rental_order全部字段
    * @param record 要更新成为的RentalOrder对象
    * @return int 更新记录数
     */
    int updateByPrimaryKey(RentalOrder record);
}