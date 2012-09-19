package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.DocLog;

public class DocLogMapper implements RowMapper<DocLog> {

  @Override
  public DocLog mapRow(ResultSet rs, int rowNum) throws SQLException {
    DocLog docLog = new DocLog();
    docLog.setDocid(rs.getString("docid"));
    docLog.setContent(rs.getString("content"));
    docLog.setTime(rs.getDate("time"));
    docLog.setUserName(rs.getString("user_name"));
    return docLog;
  }

}
