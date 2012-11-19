package com.iqbon.jcms.service.quartz;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import com.iqbon.jcms.domain.Quartz;
import com.iqbon.jcms.util.BeanFactory;

public class QuartzServiceTest extends TestCase {

  private static final Logger logger = Logger.getLogger(QuartzServiceTest.class);
  private QuartzService quartzService;

  protected void setUp() throws Exception {
    super.setUp();
    quartzService = (QuartzService) BeanFactory.getBean("quartzService");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testGetQuartzJobList() {
    List<Quartz> list = quartzService.getQuartzJobList();
    for (Quartz quartz : list) {
      logger.info(ToStringBuilder.reflectionToString(quartz));
    }
  }

  public void testAddParseModelJob() {
    List<String> topicIds = new ArrayList<String>();
    topicIds.add("135srerwer");
    try {
      quartzService.addParseModelJob("testjob", topicIds, "afwer", "*/2", "*");
    } catch (SchedulerException e) {
      logger.error("添加定时任务出错", e);
    }
  }

  public void testDeleteJob() {
    try {
      quartzService.deleteJob("testjob");
    } catch (SchedulerException e) {
      logger.error("删除定时任务出错", e);
    }
  }

  public void testSetQuartzStatus() {
    fail("Not yet implemented");
  }

  public void testGetQuartzStatus() {
    fail("Not yet implemented");
  }

}
