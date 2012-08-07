package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.Model;

public class ModelMapper implements RowMapper<Model> {

  @Override
  public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
    Model model = new Model();
    model.setAddTime(rs.getString("add_time"));
    model.setContent(rs.getString("content"));
    model.setDelete(rs.getInt("del"));
    model.setExtname(rs.getString("extname"));
    model.setKeyword(rs.getString("keyword"));
    model.setLastModify(rs.getString("last_modify"));
    model.setModelName(rs.getString("model_name"));
    model.setModifyUser(rs.getString("modify_user"));
    model.setRate(rs.getInt("rate"));
    model.setTimeout(rs.getString("time_out"));
    model.setTitle(rs.getString("title"));
    model.setTopicid(rs.getString("topicid"));
    model.setType(rs.getInt("type"));
    model.setUrl(rs.getString("url"));
    model.setStatus(rs.getInt("status"));
    model.setAddTime(rs.getString("add_time"));
    return model;
  }

}
