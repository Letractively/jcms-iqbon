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

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
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
  public void setDataSource(@Qualifier("dataSource")
  DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 插入系统码
   * @param code
   * @return
   */
  @TriggersRemove(cacheName = "codeCache", removeAll = true)
  public int insertCode(Code code) {
    String sql = "insert into sys_code(group_name,`key`,`value`,parent_key,last_modify) values (:groupName,:key,:value,:parentKey,now())";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(code);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 删除单个系统码
   * @param groupName
   * @param key
   * @return
   */
  @TriggersRemove(cacheName = "codeCache", removeAll = true)
  public int deleteCode(String groupName, String key) {
    String sql = "delete from sys_code where group_name = :groupName and `key`=:key";
    Map<String, String> map = new HashMap<String, String>();
    map.put("groupName", groupName);
    map.put("key", key);
    return namedParameterJdbcTemplate.update(sql, map);
  }

  /**
   * 按组删除系统码
   * @param groupName
   * @return
   */
  @TriggersRemove(cacheName = "codeCache", removeAll = true)
  public int deleteCodeByGroup(String groupName) {
    String sql = "delete from sys_code where group_name =:groupName";
    Map<String, String> map = new HashMap<String, String>();
    map.put("groupName", groupName);
    return namedParameterJdbcTemplate.update(sql, map);
  }

  /**
   * 根据参数组名和父参数，获取所有子参数
   * @param groupName
   * @return
   */
  @Cacheable(cacheName = "codeCache")
  public List<Code> querySubCodeByGroupAndParent(String groupName,String parentKey) {
    String sql = "select group_name,`key`,`value`,parent_key from sys_code where group_name = :groupName and parent_key = :parentKey and parent_key!='' order by last_modify desc";
    Map<String, String> map = new HashMap<String, String>();
    map.put("groupName", groupName);
    map.put("parentKey", parentKey);
    return namedParameterJdbcTemplate.query(sql, map, new CodeMapper());
  }

  /**
   * 获取系统中所有的系统码组
   * @return
   */
  @Cacheable(cacheName = "codeCache")
  public List<Code> queryAllGroup() {
    String sql = "select group_name,`key`,`value`,parent_key , group_name from sys_code where parent_key = '' order by group_name desc";
    Map<String, String> map = new HashMap<String, String>();
    return namedParameterJdbcTemplate.query(sql, map, new CodeMapper());
  }

  /**
   * 根据系统码组名获取系统码组信息
   * @param groupName
   * @return
   */
  @Cacheable(cacheName = "codeCache")
  public Code queryGroupInfo(String groupName) {
    String sql = "select group_name,`key`,`value`,parent_key , group_name from sys_code where parent_key = '' and value =:groupName";
    Map<String, String> map = new HashMap<String, String>();
    map.put("groupName", groupName);
    return namedParameterJdbcTemplate.queryForObject(sql, map, new CodeMapper());
  }

  /**
   * 查找同一组中存在相同key值的数量
   * @param groupName
   * @param key
   * @return
   */
  public int queryCodeNumByGroupAndKey(String groupName, String key) {
    String sql = "select count(1) from sys_code where group_name = :groupName and `key` = :key";
    Map<String, String> map = new HashMap<String, String>();
    map.put("groupName", groupName);
    map.put("key", key);
    return namedParameterJdbcTemplate.queryForInt(sql, map);
  }

}
