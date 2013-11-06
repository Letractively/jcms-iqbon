package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.PushRecord;

public class PushRecordMapper implements RowMapper<PushRecord> {

  @Override
  public PushRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
    PushRecord pushRecord = new PushRecord();
    pushRecord.setIndexid(rs.getString("indexid"));
    pushRecord.setDocid(rs.getString("docid"));
    pushRecord.setModelName(rs.getString("modelname"));
    pushRecord.setTitle(rs.getString("title"));
    pushRecord.setLspri(rs.getInt("lspri"));
    pushRecord.setUrl(rs.getString("url"));
    pushRecord.setSubTitle(rs.getString("sub_title"));
    pushRecord.setTopicid(rs.getString("topicid"));
    pushRecord.setLastModify(rs.getString("last_modify"));
    pushRecord.setAddDate(rs.getString("add_date"));
    pushRecord.setModifyUser(rs.getString("modify_user"));
    pushRecord.setType(rs.getInt("type"));
    pushRecord.setImg(rs.getString("img"));
    return pushRecord;
    
  }

}
