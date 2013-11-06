package com.iqbon.jcms.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.PushRecord;

public class VelocityToolTest extends TestCase {

  private static final Logger logger = Logger.getLogger(VelocityToolTest.class);

  private VelocityTool tool;

  protected void setUp() throws Exception {
    super.setUp();
    tool = (VelocityTool) BeanFactory.getBean("velocityTool");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testGetPushRecordByTopic() throws Exception {
    List<PushRecord> list = tool.getPushRecordByTopic("1304172154000031X7", 0, 100, null, null,
        null, 5);
    for (PushRecord pushRecord : list) {
      logger.info(ToStringBuilder.reflectionToString(pushRecord));
    }
    List<PushRecord> list2 = tool.getPushRecordByTopic("1304172154000031X7", 5);
    for (PushRecord pushRecord : list2) {
      logger.info(ToStringBuilder.reflectionToString(pushRecord));
    }
  }

  public void testIsEmptyList() {
    List<PushRecord> list = new ArrayList<PushRecord>();
    assertFalse(!tool.isEmptyList(list));
    assertFalse(!tool.isEmptyList(null));
    list.add(new PushRecord());
    assertFalse(tool.isEmptyList(list));
  }

  public void testNextPushRecord() {
    PushRecord pushRecord = tool.nextPushRecord("7", 60, 60, null);
    logger.info(ToStringBuilder.reflectionToString(pushRecord));
  }

  public void testSplit2List() {
    List<String> strs = tool.split2List("唐三,斗罗大陆", ",");
    logger.info(strs.size());
  }

}
