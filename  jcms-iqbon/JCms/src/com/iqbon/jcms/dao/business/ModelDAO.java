package com.iqbon.jcms.dao.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 模板DAO
 * @author hp
 *
 */
@Repository
public class ModelDAO {
  
  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


}
