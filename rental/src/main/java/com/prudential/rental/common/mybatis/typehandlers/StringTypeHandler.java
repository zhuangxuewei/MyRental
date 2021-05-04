package com.prudential.rental.common.mybatis.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.prudential.rental.common.util.DateUtil;

public class StringTypeHandler extends BaseTypeHandler<Object> {
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, objectToString(parameter));
	}

	public Object getResult(ResultSet resultset, String columnName) throws SQLException {
		Object s = resultset.getObject(columnName);
		return objectToString(s);
	}

	public Object getResult(CallableStatement callablestatement, int columnIndex) throws SQLException {
		Object s = callablestatement.getObject(columnIndex);
		return objectToString(s);
	}

	public Object getNullableResult(ResultSet resultset, String columnName) throws SQLException {
		Object s = resultset.getObject(columnName);
		return objectToString(s);
	}

	public Object getNullableResult(ResultSet resultset, int columnIndex) throws SQLException {
		Object s = resultset.getObject(columnIndex);
		return objectToString(s);
	}

	public Object getNullableResult(CallableStatement callablestatement, int columnIndex) throws SQLException {
		Object s = callablestatement.getObject(columnIndex);
		return objectToString(s);
	}

	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.toString());
	}

	private String objectToString(Object obj) {
		if (obj == null)
			return "";
		if ((obj instanceof Date)) {
			return DateUtil.format((Date) obj);
		}
		return obj.toString();
	}
}