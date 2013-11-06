package com.iqbon.jcms.dao.business;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.util.BeanFactory;
import com.iqbon.jcms.web.util.Page;

public class PushRecordDAOTest extends TestCase {

  private static final Logger logger = Logger.getLogger(PushRecordDAOTest.class);
  private PushRecordDAO pushRecordDAO;
  private PushRecord pushRecord;  
  
  protected void setUp() throws Exception {
    pushRecordDAO = (PushRecordDAO) BeanFactory.getBean("pushRecordDAO");
    pushRecord = new PushRecord();
    pushRecord.setTitle("测试推送");
    pushRecord.setTopicid("12022822300000248P");
    pushRecord.setLspri(60);
    pushRecord.setModifyUser("test");
    pushRecord.setSubTitle("测试推送子栏目");
    pushRecord.setUrl("http://blog.iqbon.com");
    pushRecord.setType(1);
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * 测试插入推送记录
   */
  public void testInsertPushRecord() {
    int num = pushRecordDAO.insertPushRecord(pushRecord);
    logger.info(num);
  }

  /**
   * 测试删除推送记录
   */
  public void testDeletePushRecord() {
    int num = pushRecordDAO.deletePushRecord(1);
    logger.info(num);
  }

  /**
   * 测试根据栏目查询推送记录
   */
  public void testQueryDocPushRecordByTopic() {
    Page page = new Page(10, 1);
    List<PushRecord> list = pushRecordDAO.queryDocPushRecordByTopic(pushRecord.getTopicid(), 0,
        page.getPageSize());
    logger.info(list.size());
    for (PushRecord pushRecord : list) {
      logger.info(ToStringBuilder.reflectionToString(pushRecord));
    }
  }
  
  
  public void testQueryDocPushRecord() {
    int num = pushRecordDAO.queryPushRecordNumByTopicAndType(pushRecord.getTopicid(), pushRecord
        .getType());
    logger.info(num);
  }
  
  public void testQueryPushRecordNumByTopic() {
    int num = pushRecordDAO.queryPushRecordNumByTopic(pushRecord.getTopicid());
    logger.info(num);
  }

  public void testUpdatePushRecord() {
    pushRecord.setTitle("更新标题");
    pushRecord.setIndexid("6");
    int num = pushRecordDAO.updatePushRecord(pushRecord);
    logger.info(num);
  }

  public void testQueryPushRecordById() {
    PushRecord pushRecord = pushRecordDAO.queryPushRecordById("7");
    logger.info(ToStringBuilder.reflectionToString(pushRecord));
  }

  public void testQueryPushRecordsById() {
    String[] indexList = {"1","2"};
    List<PushRecord> list = pushRecordDAO.queryPushRecordsById(Arrays.asList(indexList));
    for(PushRecord p:list){
      logger.info(ToStringBuilder.reflectionToString(p));
    }
  }

  public void testDeletePushRecords() {
    int number = pushRecordDAO.deletePushRecords(null);
    logger.info(number);
  }
}
