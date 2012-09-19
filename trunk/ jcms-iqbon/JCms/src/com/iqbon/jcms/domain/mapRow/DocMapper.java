package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.Doc;

public class DocMapper implements RowMapper<Doc> {

  @Override
  public Doc mapRow(ResultSet rs, int rowNum) throws SQLException {
    Doc doc = new Doc();
    doc.setDocid(rs.getString("docid"));
    doc.setTitle(rs.getString("title"));
    doc.setContent(rs.getString("content"));
    doc.setDigest(rs.getString("digest"));
    doc.setModifyUser(rs.getString("modify_user"));
    doc.setDelete(rs.getInt("del"));
    doc.setReporter(rs.getString("reporter"));
    doc.setLastModify(rs.getString("last_modify"));
    doc.setUrl(rs.getString("url"));
    doc.setType(rs.getInt("type"));
    doc.setKeyword(rs.getString("keyword"));
    doc.setModelName(rs.getString("model_name"));
    doc.setStatus(rs.getInt("status"));
    return doc;
  }

}
