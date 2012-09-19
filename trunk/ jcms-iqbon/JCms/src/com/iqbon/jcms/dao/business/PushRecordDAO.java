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
    String sql = "select indexid,docid,modelname,title,lspri,url,sub_title,add_date,last_modify,modify_user,topicid,type,img "
        + "from bu_push_record where  topicid = :topicid  order by add_date desc,lspri desc,last_modify desc limit :begin,:size";
    Map<String, Object> map = new HashMap<String, Object>(3);
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
    String sql = "insert into bu_push_record(docid,modelname,title,lspri,url,sub_title,add_date,last_modify,modify_user,topicid,type,img )"
        + " values(:docid,:modelName,:title,:lspri,:url,:subTitle,now(),now(),:modifyUser,:topicid,:type,:img)";
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
    Map<String, Integer> map = new HashMap<String, Integer>(1);
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
    Map<String, Object> map = new HashMap<String, Object>(2);
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
    Map<String, String> map = new HashMap<String, String>(1);
    map.put("topicid", topicid);
    return namedParameterJdbcTemplate.queryForInt(sql, map);
  }
  
  /**
   * 更新推送记录
   * @param pushRecord
   * @return
   */
  public int updatePushRecord(PushRecord pushRecord) {
    String sql = "update bu_push_record set docid=:docid,modelname=:modelName,title=:title,"
        + "lspri=:lspri,url=:url,sub_title=:subTitle,last_modify = now(),"
        + "modify_user=:modifyUser,topicid=:topicid,img=:img "
        + "where indexid=:indexid";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(pushRecord);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 根据ID查找推送记录信息
   * @param indexid
   * @return
   */
  public PushRecord queryPushRecordById(int indexid) {
    String sql = "select * from bu_push_record where indexid=:indexid";
    Map<String, Integer> map = new HashMap<String, Integer>(1);
    map.put("indexid", indexid);
    return namedParameterJdbcTemplate.queryForObject(sql, map, new PushRecordMapper());
  }

  /**
   * 批量删除推送记录
   * @param indexidList
   * @return
   */
  @SuppressWarnings("unchecked")
  public int deletePushRecords(List<String> indexidList) {
    String sql = "delete from bu_push_record where indexid=:indexid";
    Map<String, String>[] mapArray = new HashMap[indexidList.size()];
    for (int i = 0; i < mapArray.length; i++) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("indexid", indexidList.get(i));
      mapArray[i] = map;
    }
    return namedParameterJdbcTemplate.batchUpdate(sql, mapArray).length;
  }

  /**
   * 根据ID批量获取推送记录
   * @param indexidList
   * @return
   */
  public List<PushRecord> queryPushRecordsById(List<String> indexidList) {
    String sql = "select * from bu_push_record where indexid in "+indexidList.toString().replace("[", "(").replace("]", ")");
    Map<String, String> map = new HashMap<String, String>(0);
    return namedParameterJdbcTemplate.query(sql, map, new PushRecordMapper());
  }

  /**
   * 批量增加推送记录
   * @param list
   * @return
   */
  public int insertPushRecords(List<PushRecord> list) {
    String sql = "insert into bu_push_record(docid,modelname,title,lspri,url,sub_title,add_date,last_modify,modify_user,topicid,type,img )"
        + " values(:docid,:modelName,:title,:lspri,:url,:subTitle,now(),now(),:modifyUser,:topicid,:type,:img)";
    SqlParameterSource[] paramMap  = new SqlParameterSource[list.size()];
    for (int i = 0; i < paramMap.length; i++) {
      paramMap[i] = new BeanPropertySqlParameterSource(list.get(i));
    }
    return namedParameterJdbcTemplate.batchUpdate(sql, paramMap).length;
  }

  /**
   * 删除指定文章的所有推送记录
   * @param docid
   * @return
   */
  public int deletePushRecordByDocid(String docid) {
    String sql = "delete from bu_push_record where docid = :docid";
    Map<String, String> map = new HashMap<String, String>(1);
    map.put("docid", docid);
    return namedParameterJdbcTemplate.update(sql, map);
  }
}
