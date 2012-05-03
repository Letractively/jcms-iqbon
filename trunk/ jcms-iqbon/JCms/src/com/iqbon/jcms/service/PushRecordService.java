package com.iqbon.jcms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.PushRecordDAO;
import com.iqbon.jcms.domain.PushRecord;

/**
 * 推送记录service类
 * @author hp
 *
 */
@Service
public class PushRecordService {
  
  private PushRecordDAO pushRecordDAO;

  @Autowired
  public void setPushRecordDAO(PushRecordDAO pushRecordDAO) {
    this.pushRecordDAO = pushRecordDAO;
  }

  /**
   * 
   * 根据栏目ID，推送类型，分页获取所在栏目的推送记录列表，按权重，最后修改时间排序
   * @param topicid
   * @param type 0 非模板 1 模板 
   * @param pageNum
   * @param pageSize
   * @return
   */
  public List<PushRecord> getPushRecordByTopicid(String topicid, int type, int pageNum, int pageSize) {
    int begin = (pageNum - 1) * pageSize;
    List<PushRecord> list = new ArrayList<PushRecord>();
    if (type == 0) {
      list = pushRecordDAO.queryDocPushRecordByTopic(topicid, begin, pageSize);
    } else if (type == 1) {
      list = pushRecordDAO.queryModelPushRecordByTopic(topicid, begin, pageSize);
    }
    return list;
  }

  /**
   * 根据栏目ID、推送类型，获取栏目下文章的推送记录数量
   * @param topicid
   * @param type
   * @return
   */
  public int getPushRecordNumByTopicAndType(String topicid, int type) {
    return pushRecordDAO.queryPushRecordNumByTopicAndType(topicid, type);
  }
}
