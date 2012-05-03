package com.iqbon.jcms.domain.mapRow;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.iqbon.jcms.domain.Topic;

/**
 * 栏目的映射类
 * @author hp
 *
 */
public class TopicMapper implements RowMapper<Topic> {

  @Override
  public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
    Topic topic = new Topic();
    topic.setTopicId(rs.getString("topicid"));
    topic.setTopicName(rs.getString("topic_name"));
    topic.setTopicTree(rs.getString("topic_tree"));
    topic.setLastModify(rs.getString("last_modify"));
    topic.setModifyUser(rs.getString("modify_user"));
    return topic;
  }

}
