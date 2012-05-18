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

import com.googlecode.ehcache.annotations.Cacheable;
import com.iqbon.jcms.domain.Code;
import com.iqbon.jcms.domain.mapRow.CodeMapper;

/**
 * 系统码表DAO
 * @author hp
 *
 */
@Repository
public class CodeDAO {
  
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 插入系统码
   * @param code
   * @return
   */
  public int insertCode(Code code) {
    String sql = "insert into sys_code(group_name,`key`,`value`,parent_key) values (:groupName,:key,:value,:parentKey)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(code);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 删除单个系统码
   * @param groupName
   * @param key
   * @return
   */
  public int deleteCode(String groupName, String key, String parentKey) {
    String sql = "delete from sys_code where group_name = :groupName and `key`=:key and parent_key = :parentKey";
    Map<String, String> map = new HashMap<String, String>();
    map.put("groupName", groupName);
    map.put("key", key);
    map.put("parentKey", parentKey);
    return namedParameterJdbcTemplate.update(sql, map);
  }

  /**
   * 按组删除系统码
   * @param groupName
   * @return
   */
  public int deleteCodeByGroup(String groupName) {
    String sql = "delete from sys_code where group_name =:groupName";
    Map<String, String> map = new HashMap<String, String>();
    map.put("groupName", groupName);
    return namedParameterJdbcTemplate.update(sql, map);
  }

  /**
   * 按组查找系统码
   * @param groupName
   * @return
   */
  public List<Code> queryCodeByGroup(String groupName) {
    String sql = "select group_name,`key`,`value`,parent_key from sys_code where group_name = :groupName";
    Map<String, String> map = new HashMap<String, String>();
    map.put("groupName", groupName);
    return namedParameterJdbcTemplate.query(sql, map, new CodeMapper());
  }

  /**
   * 获取系统中所有的组
   * @return
   */
  @Cacheable(cacheName = "codeCache")
  public List<Code> queryAllGroup() {
    String sql = "select group_name,`key`,`value`,parent_key , group_name from sys_code where parent_key = group_name";
    Map<String, String> map = new HashMap<String, String>();
    return namedParameterJdbcTemplate.query(sql, map, new CodeMapper());
  }
}
