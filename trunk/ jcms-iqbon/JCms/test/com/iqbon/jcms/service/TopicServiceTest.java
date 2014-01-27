package com.iqbon.jcms.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.util.BeanFactory;

public class TopicServiceTest extends TestCase {

  private Logger logger = Logger.getLogger(TopicServiceTest.class);
  
  TopicService topicService;
  Topic topic = new Topic();
  
  protected void setUp() throws Exception {
    super.setUp();
    topicService = (TopicService) BeanFactory.getBean("topicService");
    topic = new Topic();
    String topicid = "12022822300000248P";//JCMSConstant.createTopicId();
    topic.setTopicId(topicid);
    topic.setTopicName("测试栏目");
    topic.setTopicTree(topicid + ";");
    topic.setModifyUser("zlliang");
    
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testGetTopTopicList() {
    List<Topic> list = topicService.getTopTopicList();
    logger.info(list.size());
    for (Topic topic : list) {
      logger.info(ToStringBuilder.reflectionToString(topic));
    }
  }

  public void testGetSubTopicList() {
    List<Topic> list = topicService.getSubTopicList(topic.getTopicId());
    logger.info(list.size());
    for(Topic topic:list ){
      logger.info(ToStringBuilder.reflectionToString(topic));
    }
  }
  
  public void testAddTopic() {
    String topicName = "测试子栏目";
    String user = "zlliang";
    
    try {
      String topicid = topicService.addTopic(topic.getTopicId(), topicName, null, user);
      logger.info(topicid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //测试增加根栏目
    try {
      String topicid = topicService.addTopic(null, topicName, null, user);
      logger.info(topicid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  public void testDeleteTopic() {
    String topicName = "测试子栏目";
    String user = "zlliang";
    try {
      String topicid = topicService.addTopic(null, topicName, null, user);
      int deletedNum = topicService.deleteTopic(topicid);
      logger.info("topicid:" + topicid);
      logger.info(deletedNum);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      int deletedNum = topicService.deleteTopic(topic.getTopicId());
      logger.info(deletedNum);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  public void testModifyTopic() {
    String topicName = "测试修改栏目2";
    
    String topicid = topic.getTopicId();
    int num = topicService.modifyTopic(topicName, topicid, null, topic.getModifyUser());
    logger.info(num);
    topicService.modifyTopic(topic.getTopicName(), topic.getTopicId(), null, topic.getModifyUser());
  }

}
