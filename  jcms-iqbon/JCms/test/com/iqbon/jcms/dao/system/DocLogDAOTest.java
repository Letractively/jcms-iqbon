package com.iqbon.jcms.dao.system;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.DocLog;
import com.iqbon.jcms.util.BeanFactory;

public class DocLogDAOTest extends TestCase {

  private Logger logger = Logger.getLogger(DocLogDAOTest.class);
  private DocLog docLog = new DocLog();
  private DocLogDAO docLogDAO;

  protected void setUp() throws Exception {
    super.setUp();
    docLogDAO = (DocLogDAO) BeanFactory.getBean("docLogDAO");
    docLog.setContent("test");
    docLog.setDocid("11111111111");
    docLog.setTime(new Date());
    docLog.setUserName("testuser");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInsertDocLog() {
    int number = docLogDAO.insertDocLog(docLog);
    logger.info(number);
  }

  public void testQueryDocLogByDocid() {
    List<DocLog> list = docLogDAO.queryDocLogByDocid(docLog.getDocid());
    for (DocLog docLog : list) {
      logger.info(ToStringBuilder.reflectionToString(docLog));
    }
  }

}
