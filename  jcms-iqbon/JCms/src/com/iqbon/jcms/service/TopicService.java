package com.iqbon.jcms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.PushRecordDAO;
import com.iqbon.jcms.dao.business.TopicDAO;
import com.iqbon.jcms.domain.Topic;
import com.iqbon.jcms.util.JCMSConstant;

/**
 * 栏目服务类
 * @author hp
 *
 */
@Service
public class TopicService {
  
  private Logger logger = Logger.getLogger(TopicService.class);
  @Autowired
  private TopicDAO topicDAO;
  @Autowired
  private PushRecordDAO pushRecordDAO;

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
   * 增加栏目
   * @param parentTopicid 父栏目ID，为空则在增加根栏目
   * @param topicName 
   * @param user
   * @return 生成的栏目ID
   */
  public String addTopic(String parentTopicid, String topicName, String user) throws Exception {
    String parentTree = "";
    if (!StringUtils.isEmpty(parentTopicid)) {
      Topic parentTopic = topicDAO.queryTopicById(parentTopicid);
      if (parentTopic == null) {
        logger.error(parentTopicid + "增加子栏目，" + parentTopicid + "不存在");
        throw new Exception(parentTopicid + "栏目不存在");
      }
      parentTree = parentTopic.getTopicTree();
    }
    String topicid = JCMSConstant.createTopicId();
    String topicTree = parentTree + topicid + ";";
    Topic topic = new Topic();
    topic.setTopicId(topicid);
    topic.setTopicTree(topicTree);
    topic.setModifyUser(user);
    topic.setTopicName(topicName);
    topic.setParentTopic(parentTopicid);
    int num = topicDAO.insertTopic(topic);
    if (num == 0) {
      throw new Exception(parentTopicid + "增加子栏目失败");
    }
    return topicid;
  }

  /**
   * 删除栏目
   * @param topicid
   * @return
   */
  public int deleteTopic(String topicid) throws Exception {
    int deletedNum = 0;
    int pushNum = pushRecordDAO.queryPushRecordNumByTopic(topicid);
    if(pushNum>0){
      logger.warn("删除栏目" + topicid + "失败，栏目下还有推送记录");
      throw new Exception("栏目下还有推送记录");
    } else {
      deletedNum = topicDAO.deleteTopic(topicid);
    }
    return deletedNum;
  }

  /**
   * 更新栏目信息
   * @param topicName
   * @param topicid
   * @return
   */
  public int modifyTopic(String topicName, String topicid, String user) {
    Topic topic = new Topic();
    topic.setTopicId(topicid);
    topic.setTopicName(topicName);
    topic.setModifyUser(user);
    return topicDAO.updateTopic(topic);
  }

  /**
   * 获取栏目信息
   * @param topicid
   * @return
   */
  public Topic getTopicInfo(String topicid) {
    return topicDAO.queryTopicById(topicid);
  }

  /**
   * 批量删除栏目
   * @param topicList
   * @return
   */
  public int batchDeleteTopics(List<String> topicList) {
    return topicDAO.batchDeleteTopic(topicList);
  }
}
