package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.ModelLog;

public class ModelLogMapper implements RowMapper<ModelLog> {

  @Override
  public ModelLog mapRow(ResultSet rs, int rowNum) throws SQLException {
    ModelLog modelLog = new ModelLog();
    modelLog.setContent(rs.getString("content"));
    modelLog.setModelContent(rs.getString("model_content"));
    modelLog.setModelName(rs.getString("model_name"));
    modelLog.setTime(rs.getString("time"));
    modelLog.setUserName(rs.getString("user_name"));
    modelLog.setId(rs.getInt("id"));
    return modelLog;
  }

}
