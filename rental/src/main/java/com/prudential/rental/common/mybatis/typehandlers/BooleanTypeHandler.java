package com.prudential.rental.common.mybatis.typehandlers;


import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class BooleanTypeHandler extends BaseTypeHandler<Object>
{
  public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
    throws SQLException
  {
    ps.setString(i, objectToBoolean(parameter) ? "Y" : "N");
  }

  public Object getResult(ResultSet resultset, String columnName) throws SQLException
  {
    Object s = resultset.getObject(columnName);
    return Boolean.valueOf(objectToBoolean(s));
  }

  public Object getResult(CallableStatement callablestatement, int columnIndex) throws SQLException
  {
    Object s = callablestatement.getObject(columnIndex);
    return Boolean.valueOf(objectToBoolean(s));
  }

  public Object getNullableResult(ResultSet resultset, String columnName) throws SQLException
  {
    Object s = resultset.getObject(columnName);
    return Boolean.valueOf(objectToBoolean(s));
  }

  public Object getNullableResult(ResultSet resultset, int columnIndex) throws SQLException
  {
    Object s = resultset.getObject(columnIndex);
    return Boolean.valueOf(objectToBoolean(s));
  }

  public Object getNullableResult(CallableStatement callablestatement, int columnIndex) throws SQLException
  {
    Object s = callablestatement.getObject(columnIndex);
    return Boolean.valueOf(objectToBoolean(s));
  }

  public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
  {
    ps.setString(i, objectToBoolean(parameter) ? "Y" : "N");
  }

  private boolean objectToBoolean(Object obj)
  {
    if (obj == null)
      return false;
    if ((obj instanceof Boolean)) {
      return ((Boolean)obj).booleanValue();
    }
    return "Y".equals(obj);
  }
}
