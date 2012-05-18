package com.iqbon.jcms.dao.business;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.util.BeanFactory;

public class TopicDAOTest extends TestCase {
  
  private final Logger logger = Logger.getLogger(TopicDAOTest.class);
  
  private TopicDAO topicDAO;
  private Topic topic;
  
  protected void setUp() throws Exception {
    topicDAO = (TopicDAO) BeanFactory.getBean("topicDAO");
    topic = new Topic();
    String topicid = "12022822300000248P";//JCMSConstant.createTopicId();
    topic.setTopicId(topicid);
    topic.setTopicName("测试栏目");
    topic.setTopicTree(topicid+";");
    topic.setModifyUser("zlliang");
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testQueryTopTopicList() {
    List<Topic> list = topicDAO.queryTopTopicList();
    logger.info("list length:" + list.size());
    for (Topic topic : list) {
      
      logger.info(ToStringBuilder.reflectionToString(topic));
    }
  }
  
  public void testInsertTopic() {
    int num = topicDAO.insertTopic(topic);
    logger.info(num);
  }

  public void testDeleteTopic() {
    int num = topicDAO.deleteTopic("12022822300000248P");
    logger.info(num);
  }
  
  public void testQuerySubTopic(){
    List<Topic> list = topicDAO.querySubTopicList("12022822300000248P");
    logger.info("list size:" + list.size());
    for (Topic topic : list) {
      logger.info(ToStringBuilder.reflectionToString(topic));
    }
  }
  
  public void testQueryTopicById() {
    Topic parentTopic = topicDAO.queryTopicById(topic.getTopicId());
    logger.info(ToStringBuilder.reflectionToString(parentTopic));
  }
}