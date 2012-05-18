package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.Code;

public class CodeMapper implements RowMapper<Code> {

  @Override
  public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
    Code code = new Code();
    code.setGroupName(rs.getString("group_name"));
    code.setKey(rs.getString("key"));
    code.setParentKey(rs.getString("parent_key"));
    code.setValue(rs.getString("value"));
    return code;
  }

}
