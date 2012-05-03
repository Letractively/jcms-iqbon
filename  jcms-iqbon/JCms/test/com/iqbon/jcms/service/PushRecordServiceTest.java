package com.iqbon.jcms.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.PushRecord;
import com.iqbon.jcms.util.BeanFactory;
import com.iqbon.jcms.web.util.ActionUtil;

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
    List<PushRecord> list = pushRecordService.getPushRecordByTopicid(pushRecord.getTopicid(), 0, 1,
        ActionUtil.DEFAUL_PAGE_SIZE);
    logger.info(list.size());
    for(PushRecord pushRecord:list){
      logger.info(ToStringBuilder.reflectionToString(pushRecord));
    }
  }

}
