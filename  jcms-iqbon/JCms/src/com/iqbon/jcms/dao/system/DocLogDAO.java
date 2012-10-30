package com.iqbon.jcms.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iqbon.jcms.domain.DocLog;
import com.iqbon.jcms.domain.mapRow.DocLogMapper;

/**
 * 文章日志DAO
 * @author hp
 *
 */
@Repository
public class DocLogDAO {
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(@Qualifier("dataSource")
  DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 插入一条文章日志
   * @param docLog
   * @return
   */
  public int insertDocLog(DocLog docLog) {
    String sql = "insert into sys_doc_log(docid,user_name,time,content) values(:docid,:userName,:time,:content)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(docLog);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 根据文章ID查询文章的操作日志
   * @param docid
   * @return
   */
  public List<DocLog> queryDocLogByDocid(String docid) {
    String sql = "select * from sys_doc_log where docid =:docid";
    Map<String, String> map = new HashMap<String, String>(1);
    map.put("docid", docid);
    return namedParameterJdbcTemplate.query(sql, map, new DocLogMapper());
  }
}
