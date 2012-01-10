package com.iqbon.jcms.dao.business;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**文章DAO类
 * @author hp
 *
 */
@Repository
public class DocDAO {
  
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }
}
