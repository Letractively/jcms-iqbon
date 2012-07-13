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

import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.domain.mapRow.TopicMapper;

/**
 * 栏目的DAO类
 * @author hp
 *
 */
@Repository
public class TopicDAO {

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  public void setDataSource(DataSource dataSource) {
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  /**
   * 查找所有的顶级栏目
   * @return
   */
  public List<Topic> queryTopTopicList() {
    String sql = "select * from bu_topic where parent_topic is null order by topic_name desc";
    Map<String, String> map = new HashMap<String, String>();
    SqlParameterSource paramMap = new MapSqlParameterSource(map);
    return namedParameterJdbcTemplate.query(sql, paramMap, new TopicMapper());
  }

  /**
   * 插入栏目信息
   * @param topic
   * @return
   */
  public int insertTopic(Topic topic) {
    String sql = "insert into bu_topic(topicid,topic_name,last_modify,modify_user,topic_tree,parent_topic)"
        + " values (:topicId,:topicName,now(),:modifyUser,:topicTree,:parentTopic)";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(topic);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }

  /**
   * 查找子栏目
   * @param topicId
   * @return
   */
  public List<Topic> querySubTopicList(String topicId) {
    String sql = "select * from bu_topic where parent_topic = :topicId  order by topic_name desc";
    Map<String, String> map = new HashMap<String, String>();
    map.put("topicId", topicId);
    return namedParameterJdbcTemplate.query(sql, map, new TopicMapper());
  }

  /**
   * 根据ID删除指定栏目
   * @param topicid
   * @return
   */
  public int deleteTopic(String topicid){
    String sql  = "delete from bu_topic where topicid = :topicId";
    Map<String, String> map = new HashMap<String, String>();
    map.put("topicId", topicid);
    return namedParameterJdbcTemplate.update(sql, map);
  }
  
  /**
   * 批量删除栏目
   * @param topicList
   * @return
   */
  @SuppressWarnings("unchecked")
  public int batchDeleteTopic(List<String> topicList) {
    String sql = "delete from bu_topic where topicid = :topicId";
    Map<String, String>[] mapArray = new HashMap[topicList.size()];
    for (int i = 0; i < mapArray.length; i++) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("topicId", topicList.get(i));
      mapArray[i] = map;
    }
    return namedParameterJdbcTemplate.batchUpdate(sql, mapArray).length;
  }

  /**
   * 根据ID查找栏目
   * @param topicid
   * @return
   */
  public Topic queryTopicById(String topicid) {
    String sql = "select * from bu_topic where topicid = :topicid";
    Map<String, String> map = new HashMap<String, String>();
    map.put("topicid", topicid);
    return namedParameterJdbcTemplate.queryForObject(sql, map, new TopicMapper());
   }

  /**
   * 更新栏目信息
   * @param topic
   * @return
   */
  public int updateTopic(Topic topic) {
    String sql = "update bu_topic set topic_name = :topicName, modify_user=:modifyUser,last_modify = now() "
        + "where topicid=:topicId";
    SqlParameterSource paramMap = new BeanPropertySqlParameterSource(topic);
    return namedParameterJdbcTemplate.update(sql, paramMap);
  }
}
