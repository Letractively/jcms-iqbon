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
    fail("Not yet implemented");
  }

  public void testGetSubTopicList() {
    List<Topic> list = topicService.getSubTopicList(topic.getTopicId());
    logger.info(list.size());
    for(Topic topic:list ){
      logger.info(ToStringBuilder.reflectionToString(topic));
    }
  }

}
