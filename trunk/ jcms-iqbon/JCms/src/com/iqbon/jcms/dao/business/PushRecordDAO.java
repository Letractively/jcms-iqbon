package com.iqbon.jcms.dao.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.domain.mapRow.PushRecordMapper;

/**
 * 推送记录DAO
 * @author hp
 *
 */
@Repository
public class PushRecordDAO {

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 根据栏目ID，推送类型，begin,size，分页获取所在栏目的非模板推送记录(type不为2)
   * @param topicid
   * @param begin
   * @param size
   * @return
   */
  public List<PushRecord> queryDocPushRecordByTopic(String topicid, int begin,
      int size) {
    String sql = "select indexid,docid,modelname,title,lspri,url,sub_title,add_date,last_modify,modify_user,topicid,type "
        + "from bu_push_record where type !=2 and topicid = :topicid  order by lspri,last_modify  limit :begin,:size";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("topicid", topicid);
    map.put("begin", begin);
    map.put("size", size);
    SqlParameterSource paramMap = new MapSqlParameterSource(map);
    return namedParameterJdbcTemplate.query(sql, paramMap, new PushRecordMapper());
  }

  /**
   * 根据栏目ID，推送类型，begin,size，分页获取所在栏目的模板推送记录(type为2)
   * @param topicid
   * @param begin
   * @param end
   * @return
   */
  public List<PushRecord> queryModelPushRecordByTopic(String topicid, int begin, int size) {
    String sql = "select indexid,docid,modelname,title,lspri,url,sub_title,add_date,last_modify,modify_user,topicid,type "
        + "from bu_push_record where type = 2 and topicid = :topicid  order by lspri,last_modify  limit :begin,:size";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("topicid", topicid);
    map.put("begin", begin);
    map.put("size", size);
    SqlParameterSource paramMap = new MapSqlParameterSource(map);
    return namedParameterJdbcTemplate.query(sql, paramMap, new PushRecordMapper());
  }

  /**
   * 插入推送记录
   * @param pushRecord
   * @return
   */
  public int insertPushRecord(PushRecord pushRecord) {
    String sql = "insert into bu_push_record(docid,modelname,title,lspri,url,sub_title,add_date,last_modify,modify_user,topicid,type )"
        + " values(:docid,:modelName,:title,:lspri,:url,:subTitle,now(),now(),:modifyUser,:topicid,:type)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(pushRecord);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 根据ID删除单条推送记录
   * @param indexid
   * @return
   */
  public int deletePushRecord(int indexid) {
    String sql = "delete from bu_push_record where indexid = :indexid";
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("indexid", indexid);
    return namedParameterJdbcTemplate.update(sql, map);
  }

  /**
   * 根据栏目ID、推送类型，获取栏目下文章的推送记录数量
   * @param topicid
   * @return
   */
  public int queryPushRecordNumByTopicAndType(String topicid, int type) {
    String sql = "select count(indexid) from bu_push_record where type = :type and topicid = :topicid";
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("topicid", topicid);
    map.put("type", type);
    return namedParameterJdbcTemplate.queryForInt(sql, map);
  }

  /**
   * 查看栏目中有多少推送记录
   * @param topicid
   * @return
   */
  public int queryPushRecordNumByTopic(String topicid) {
    String sql = "select count(indexid) from bu_push_record where topicid = :topicid";
    Map<String, String> map = new HashMap<String, String>();
    map.put("topicid", topicid);
    return namedParameterJdbcTemplate.queryForInt(sql, map);
  }
  
}
