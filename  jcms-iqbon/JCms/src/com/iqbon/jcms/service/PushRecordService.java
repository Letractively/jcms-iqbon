package com.iqbon.jcms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.PushRecordDAO;
import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.util.JCMSConstant;

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
   * @param type 0普通推送，1模板推送
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
    pushRecord.setIndexid(JCMSConstant.createPushRecordId());
    pushRecord.setTitle(title);
    pushRecord.setSubTitle(subTitle);
    pushRecord.setTopicid(topicid);
    pushRecord.setUrl(url);
    pushRecord.setImg(img);
    pushRecord.setLspri(lspri);
    pushRecord.setType(0);
    pushRecord.setModifyUser(userName);
    return addPushRecord(pushRecord);
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
  public int updateLspri(String indexid, int lspri, String userName) {
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
  public PushRecord getPushRecordById(String indexid) {
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
      pushRecord.setIndexid(JCMSConstant.createPushRecordId());
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
      pushRecord.setIndexid(JCMSConstant.createPushRecordId());
      insertList.add(pushRecord);
    }
    pushRecordDAO.deletePushRecords(indexList);
    return pushRecordDAO.insertPushRecords(insertList);
  }
  
  /**
   * 获取指定栏目的推送列表
   * @param topicid 栏目ID
   * @param minLspri 最小权重 默认为0
   * @param maxLspri 最大权重 默认为100
   * @param isImg 是否有图片
   * @param startTime 时间范围，最后修改时间在startTime之后，可以为空
   * @param endTime 时间范围，最后修改时间在startTime之前，可以为空
   * @param limit 获取多少条记录
   * @return
   */
  public List<PushRecord> getPushRecordByTopic(String topicid, int minLspri, int maxLspri,
      Boolean isImg, String startTime, String endTime, int limit) {
    return pushRecordDAO.queryPushRecordByTopic(topicid, minLspri, maxLspri, isImg, startTime,
        endTime,
        limit);
  }

  /**
   * 获取文章的所有推送记录
   * @param docid
   * @return
   */
  public List<PushRecord> getPushRecordByDocid(String docid) {
    return pushRecordDAO.queryPushRecordByDocid(docid);
  }

  /**
   * 根据推送记录ID，获取前一推送记录
   * @param indexid  推送记录ID
   * @param minLspri 权重最小值0-100
   * @param maxLspri 权重最大值0-100
   * @param isImg 是否图片，可以为null
   * @return
   */
  public PushRecord getNextPushRecord(String indexid, int minLspri, int maxLspri, Boolean isImg) {
    PushRecord pushRecord = pushRecordDAO.queryPushRecordById(indexid);
    if(pushRecord==null){
      return null;
    }else{
      String topicid  = pushRecord.getTopicid();
      List<PushRecord> list = getPushRecordByTopic(topicid, minLspri, maxLspri, isImg, null, null,
          Integer.MAX_VALUE);
      PushRecord lastOne = null;
      for (PushRecord one : list) {
        if (one.getIndexid().equals(indexid)) {
          break;
        }
        lastOne = one;
      }
      return lastOne;
    }
  }

  /**
   * 根据推送记录ID，获取下一推送记录
   * @param indexid  推送记录ID
   * @param minLspri 权重最小值0-100
   * @param maxLspri 权重最大值0-100
   * @param isImg 是否图片，可以为null
   * @return
   */
  public PushRecord getLastPushRecord(String indexid, int minLspri, int maxLspri, Boolean isImg) {
    PushRecord pushRecord = pushRecordDAO.queryPushRecordById(indexid);
    if (pushRecord == null) {
      return null;
    } else {
      String topicid = pushRecord.getTopicid();
      List<PushRecord> list = getPushRecordByTopic(topicid, minLspri, maxLspri, isImg, null, null,
          Integer.MAX_VALUE);
      boolean found = false;
      for (PushRecord one : list) {
        if (found) {
          return one;
        }
        if (one.getIndexid().equals(indexid)) {
          found = true;
        }

      }
      return null;
    }
  }

}
