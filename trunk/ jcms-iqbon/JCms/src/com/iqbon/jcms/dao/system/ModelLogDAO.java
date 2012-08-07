package com.iqbon.jcms.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iqbon.jcms.domain.ModelLog;
import com.iqbon.jcms.domain.mapRow.ModelLogMapper;

/**
 * 模板日志DAO
 * @author hp
 *
 */
@Repository
public class ModelLogDAO {
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 插入一条模板操作日志
   * @param modelLog
   * @return
   */
  public int insertModelLog(ModelLog modelLog) {
    String sql = "insert into sys_model_log(model_name,user_name,time,content,model_content)"
        + " values(:modelName,:userName,now(),:content,:modelContent)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(modelLog);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 按照模板名查找模板操作日志
   * @param modelName
   * @return
   */
  public List<ModelLog> queryModelLogsByModelName(String modelName){
    String sql = "select * from sys_model_log where model_name = :modelName order by time desc";
    Map<String, String> map = new HashMap<String, String>();
    map.put("modelName", modelName);
    return namedParameterJdbcTemplate.query(sql, map, new ModelLogMapper());
  }


}
