package com.iqbon.jcms.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.TopicDAO;
import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.util.JCMSConstant;

@Service
public class TopicService {
  
  private Logger logger = Logger.getLogger(TopicService.class);
  private TopicDAO topicDAO;

  @Autowired
  public void setTopicDAO(TopicDAO topicDAO) {
    this.topicDAO = topicDAO;
  }

  /**
   * 查找所有顶级栏目
   * @return
   */
  public List<Topic> getTopTopicList() {
    return topicDAO.queryTopTopicList();
  }

  /**
   * 获取子栏目
   * @param topicId
   * @return
   */
  public List<Topic> getSubTopicList(String topicId) {
    return topicDAO.querySubTopicList(topicId);
  }

  /**
   * 增加栏目，增加失败返回0
   * @param parentTopicid
   * @param topicName
   * @param user
   * @return
   */
  public int addTopic(String parentTopicid, String topicName, String user) {
    Topic parentTopic = topicDAO.queryTopicById(parentTopicid);
    if(parentTopic==null){
      logger.error(parentTopicid + "增加子栏目，" + parentTopicid + "不存在");
      return 0;
    }
    String topicid = JCMSConstant.createTopicId();
    String topicTree = parentTopic.getTopicTree() + ";" + topicid;
    Topic topic = new Topic();
    topic.setTopicId(topicid);
    topic.setTopicTree(topicTree);
    topic.setModifyUser(user);
    topic.setTopicName(topicName);
    return topicDAO.insertTopic(topic);
  }
}
