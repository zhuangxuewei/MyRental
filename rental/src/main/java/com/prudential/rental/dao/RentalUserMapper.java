package com.prudential.rental.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.prudential.rental.common.mybatis.Criteria;
import com.prudential.rental.model.entity.RentalUser;

/**
 * @ClassName: RentalUserMapper
 * @Description: rental_user表对应的dao操作Mapper映射类
 * @author:
 */
@Mapper
public interface RentalUserMapper {
	/**
	 * @Title RentalUserMapper.countByExample
	 * @Description: 根据查询条件，计算rental_user个数
	 * @param example
	 *            通用查询条件类
	 * @return int 结果个数
	 */
	int countByExample(Criteria example);

	/**
	 * @Title RentalUserMapper.deleteByExample
	 * @Description: 根据查询条件，删除rental_user
	 * @param example
	 *            通用查询条件类
	 * @return int 删除个数
	 */
	int deleteByExample(Criteria example);

	/**
	 * @Title RentalUserMapper.deleteByPrimaryKey
	 * @Description: 根据属性名称，删除rental_user
	 * @param id
	 *            id
	 * @return int 删除个数
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * @Title RentalUserMapper.insert
	 * @Description: 插入一个rental_user
	 * @param record
	 *            rental_user的bean对象
	 * @return int 插入个数
	 */
	int insert(RentalUser record);

	/**
	 * @Title RentalUserMapper.insertSelective
	 * @Description: 插入一个只有部分字段的rental_user
	 * @param record
	 *            只含部分字段的rental_user的bean对象
	 * @return int 插入个数
	 */
	int insertSelective(RentalUser record);

	/**
	 * @Title RentalUserMapper.selectByExample
	 * @Description: 根据查询条件类，返回rental_user结果集
	 * @param example
	 *            通用查询条件类
	 * @return List<RentalUser>rental_user结果集
	 */
	List<RentalUser> selectByExample(Criteria example);

	/**
	 * @Title RentalUserMapper.selectByPrimaryKey
	 * @Description: 根据主键类，返回rental_user
	 * @param id
	 *            id
	 * @return RentalUser bean对象
	 */
	RentalUser selectByPrimaryKey(String id);

	/**
	 * @Title RentalUserMapper.updateByExampleSelective
	 * @Description: 根据查询条件更新rental_user部分字段
	 * @param record
	 *            要更新成为的RentalUser对象
	 * @param criteria
	 *            更新记录的查询条件
	 * @return int 更新记录数
	 */
	int updateByExampleSelective(@Param("record") RentalUser record, @Param("example") Criteria criteria);

	/**
	 * @Title RentalUserMapper.updateByExample
	 * @Description: 根据查询条件更新rental_user全表字段
	 * @param record
	 *            要更新成为的RentalUser对象
	 * @param criteria
	 *            更新记录的查询条件
	 * @return int 更新记录数
	 */
	int updateByExample(@Param("record") RentalUser record, @Param("example") Criteria criteria);

	/**
	 * @Title RentalUserMapper.updateByPrimaryKeySelective
	 * @Description: 根据主键更新rental_user部分字段
	 * @param record
	 *            要更新成为的RentalUser对象
	 * @return int 更新记录数
	 */
	int updateByPrimaryKeySelective(RentalUser record);

	/**
	 * @Title RentalUserMapper.updateByPrimaryKey
	 * @Description: 根据主键更新rental_user全部字段
	 * @param record
	 *            要更新成为的RentalUser对象
	 * @return int 更新记录数
	 */
	int updateByPrimaryKey(RentalUser record);
}