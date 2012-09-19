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
  
  @Autowired
  private PushRecordDAO pushRecordDAO;

  /**
   * 
   * 根据栏目ID，推送类型，分页获取所在栏目的推送记录列表，按权重，最后修改时间排序
   * @param topicid
   * @param type 0 非模板 1 模板 
   * @param pageNum
   * @param pageSize
   * @return
   */
  public List<PushRecord> getPushRecordByTopicid(String topicid, int pageNum, int pageSize) {
    int begin = (pageNum - 1) * pageSize;
    List<PushRecord> list = new ArrayList<PushRecord>();
    list = pushRecordDAO.queryDocPushRecordByTopic(topicid, begin, pageSize);
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

  /**
   * 增加推送记录
   * @param pushRecord
   * @return
   */
  public int addPushRecord(PushRecord pushRecord) {
    return pushRecordDAO.insertPushRecord(pushRecord);
  }

  /**
   * 增加空文章
   * @param title
   * @param subTitle
   * @param url
   * @param topicid
   * @param lspri
   * @param userName
   * @return
   */
  public int addBlankDoc(String title, String subTitle, String url, String topicid, int lspri,
      String userName, String img) {
    PushRecord pushRecord = new PushRecord();
    pushRecord.setTitle(title);
    pushRecord.setSubTitle(subTitle);
    pushRecord.setTopicid(topicid);
    pushRecord.setUrl(url);
    pushRecord.setImg(img);
    pushRecord.setLspri(lspri);
    pushRecord.setType(0);
    pushRecord.setModifyUser(userName);
    return this.addPushRecord(pushRecord);
  }

  /**
   * 更新推送记录
   * @param pushRecord
   * @return
   */
  public int updatePushRecord(PushRecord pushRecord){
    return pushRecordDAO.updatePushRecord(pushRecord);
  }

  /**
   * 更新推送记录权重
   * @param indexid
   * @param lspri
   * @return
   */
  public int updateLspri(int indexid, int lspri, String userName) {
    PushRecord pushRecord = pushRecordDAO.queryPushRecordById(indexid);
    pushRecord.setLspri(lspri);
    pushRecord.setModifyUser(userName);
    return this.updatePushRecord(pushRecord);
  }

  /**
   * 根据ID获取推送记录
   * @param indexid
   * @return
   */
  public PushRecord getPushRecordById(int indexid) {
    PushRecord pushRecord = pushRecordDAO.queryPushRecordById(indexid);
    return pushRecord;
  }

  /**
   * 批量删除推送记录
   * @param indexidList
   * @return
   */
  public int deletePushRecords(List<String> indexidList) {
    return pushRecordDAO.deletePushRecords(indexidList);
  }

  /**
   * 复制推送记录到指定栏目
   * @param indexList
   * @return
   */
  public int copyPushRecords(List<String> indexList, String topicid) {
    List<PushRecord> list = pushRecordDAO.queryPushRecordsById(indexList);
    List<PushRecord> insertList = new ArrayList<PushRecord>();
    for (PushRecord pushRecord : list) {
      pushRecord.setTopicid(topicid);
      insertList.add(pushRecord);
    }
    return pushRecordDAO.insertPushRecords(insertList);
  }

  /**
   * 剪切推送记录到栏目
   * @param indexList
   * @param topicid
   * @return
   */
  public int cutPushRecords(List<String> indexList, String topicid) {
    List<PushRecord> list = pushRecordDAO.queryPushRecordsById(indexList);
    List<PushRecord> insertList = new ArrayList<PushRecord>();
    for (PushRecord pushRecord : list) {
      pushRecord.setTopicid(topicid);
      insertList.add(pushRecord);
    }
    pushRecordDAO.deletePushRecords(indexList);
    return pushRecordDAO.insertPushRecords(insertList);
  }
  
}
