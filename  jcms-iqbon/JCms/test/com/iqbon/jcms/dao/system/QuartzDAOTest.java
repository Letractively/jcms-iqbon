package com.iqbon.jcms.dao.system;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Quartz;
import com.iqbon.jcms.util.BeanFactory;

public class QuartzDAOTest extends TestCase {

  private Logger logger = Logger.getLogger(QuartzDAOTest.class);

  private QuartzDAO quartzDAO;

  protected void setUp() throws Exception {
    super.setUp();
    quartzDAO = (QuartzDAO) BeanFactory.getBean("quartzDAO");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testSelectAllQuartJob() {
    List<Quartz> list = quartzDAO.selectAllQuartJob();
    System.out.println(list.size());
    for (Quartz quartz : list) {
      System.out.println(ToStringBuilder.reflectionToString(quartz));
    }
  }

}
