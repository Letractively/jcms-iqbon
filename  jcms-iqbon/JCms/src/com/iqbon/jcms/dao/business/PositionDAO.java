package com.iqbon.jcms.dao.business;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * 岗位DAO
 * @author hp
 *
 */
@Repository
public class PositionDAO {
  
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(@Qualifier("dataSource")
  DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 增加职位
   * @param positionName
   * @param descript
   * @return
   */
  public int insertPosition(String positionName, String descript) {
    String sql = "insert into bu_position (position_name,descript) values(:positionName,:descript)";
    Map<String, String> map = new HashMap<String, String>();
    map.put("positionName", positionName);
    map.put("descript", descript);
    SqlParameterSource paramMap = new MapSqlParameterSource(map);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }
  
  /**
   * 删除职位
   * @param positionNum
   * @return
   */
  public int deletePosition(String positionNum) {
    String sql = "delete from bu_position where position_num = :positionNum";
    SqlParameterSource paramMap = new MapSqlParameterSource("positionNum", positionNum);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }
}
