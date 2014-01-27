package com.iqbon.jcms.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.util.BeanFactory;
import com.iqbon.jcms.web.util.Page;

public class PushRecordServiceTest extends TestCase {
  
  private static final Logger logger = Logger.getLogger(PushRecordServiceTest.class);
  private PushRecordService pushRecordService;
  private PushRecord pushRecord;
  
  protected void setUp() throws Exception {
    pushRecordService = (PushRecordService) BeanFactory.getBean("pushRecordService");
    pushRecord = new PushRecord();
    pushRecord.setTitle("测试推送");
    pushRecord.setTopicid("12022822300000248P");
    pushRecord.setLspri(60);
    pushRecord.setModifyUser("test");
    pushRecord.setSubTitle("测试推送子栏目");
    pushRecord.setUrl("http://blog.iqbon.com");
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  /**
   * 测试根据栏目ID获取推送记录列表，按权重，最终更新时间排序
   */
  public void testGetPushRecordByTopicid() {
    Page page = new Page(10, 1);
    List<PushRecord> list = pushRecordService.getPushRecordByTopicid("1206282306000040cl", 1,
        page.getPageSize());
    logger.info(list.size());
    for(PushRecord pushRecord:list){
      logger.info(ToStringBuilder.reflectionToString(pushRecord));
    }
  }

  public void testGetLastPushRecord() {
    PushRecord pushRecord = pushRecordService.getLastPushRecord("7", 60, 60, null);
    logger.info(ToStringBuilder.reflectionToString(pushRecord));
  }

  public void testGetNextRecord() {
    PushRecord pushRecord = pushRecordService.getNextPushRecord("7", 60, 60, null);
    logger.info(ToStringBuilder.reflectionToString(pushRecord));
  }

  public void testAddPushRecord() {
    //    PushRecord pushRecord = new PushRecord();
    //
    //    int number = pushRecordService.addPushRecord(pushRecord);
  }

  public void testGetPushRecordNumByTopicAndType() {
    int totalNum = pushRecordService.getPushRecordNumByTopicAndType("1309050043000012EW", 0);
    logger.info(totalNum);
  }
}
