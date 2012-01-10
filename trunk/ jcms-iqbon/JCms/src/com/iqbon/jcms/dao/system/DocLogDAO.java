package com.iqbon.jcms.dao.system;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 文章日志DAO
 * @author hp
 *
 */
@Repository
public class DocLogDAO {
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }
}
