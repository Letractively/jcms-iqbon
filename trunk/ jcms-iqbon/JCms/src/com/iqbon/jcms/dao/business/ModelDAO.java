package com.iqbon.jcms.dao.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.domain.mapRow.ModelMapper;

/**
 * 模板DAO
 * @author hp
 *
 */
@Repository
public class ModelDAO {
  
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 增加一个模板
   * @param model
   * @return
   */
  public int insertModel(Model model) {
    String sql = "insert into bu_model(model_name,content,last_modify,del,modify_user,type,url,title,keyword,topicid,extname,add_time,status)"
        + " values(:modelName,:content,now(),:delete,:modifyUser,:type,:url,:title,:keyword,:topicid,:extname,now(),status)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(model);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 增加模板刷新信息
   * @param model
   * @return
   */
  public int insertModelRefresh(Model model) {
    String sql = "insert into bu_model_refresh(model_name,rate,time_out)"
        + " values(:modelName,:rate,:timeout)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(model);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 根据栏目获取模板列表
   * @param topicid
   * @return
   */
  public List<Model> queryModelByTopic(String topicid, int type) {

    String sql = "select bu_model.*,bu_model_refresh.* from bu_model,bu_model_refresh where topicid = :topicid and type=:type"
        + " and bu_model.model_name = bu_model_refresh.model_name and del = '0' order by add_time desc,last_modify desc,title desc";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("topicid", topicid);
    map.put("type", type);
    return namedParameterJdbcTemplate.query(sql, map, new ModelMapper());
  }

  /**
   * 根据栏目获取模板总数
   * @param topicid
   * @return
   */
  public int queryModelNumberByTopic(String topicid) {
    String sql = "select count(model_name) from bu_model where topicid=:topicid and del = '0'";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("topicid", topicid);
    return namedParameterJdbcTemplate.queryForInt(sql, map);
  }

  /**
   * 根据模板名获取模板信息
   * @param modelName
   * @return
   */
  public Model queryModelByModelName(String modelName) {
    String sql = "select bu_model.*,bu_model_refresh.* from bu_model,bu_model_refresh where bu_model.model_name = :modelName"
        + " and bu_model.model_name = bu_model_refresh.model_name";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("modelName", modelName);
    return namedParameterJdbcTemplate.queryForObject(sql, map, new ModelMapper());
  }

  /**
   * 更新模板信息
   * @param model
   * @return
   */
  public int updateModel(Model model) {
    String sql = "update bu_model set content=:content,last_modify=:lastModify,modify_user=:modifyUser,title=:title,keyword=:keyword,extname=:extname"
        + ",status=:status where model_name=:modelName";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(model);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 标记删除模板
   * @param modelName
   * @return
   */
  public int deleteModel(String modelName) {
    String sql = "update bu_model set del = 1 where model_name = :modelName";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("modelName", modelName);
    return namedParameterJdbcTemplate.update(sql, map);
  }

  /**
   * 更新模板刷新信息
   * @param model
   * @return
   */
  public int updateModelRefresh(Model model) {
    String sql = "update bu_model_refresh set rate=:rate,time_out=:timeout where model_name = :modelName";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(model);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

}
