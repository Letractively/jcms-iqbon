package com.iqbon.jcms.dao.system;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.ModelLog;
import com.iqbon.jcms.util.BeanFactory;

public class ModelLogDAOTest extends TestCase {

  private ModelLog modelLog = new ModelLog();
  private ModelLogDAO modelLogDAO;
  private Logger logger = Logger.getLogger(ModelLogDAOTest.class);
  protected void setUp() throws Exception {
    super.setUp();
    modelLog.setContent("cotent");
    modelLog.setModelContent("modelContent");
    modelLog.setModelName("testName");
    modelLog.setUserName("testuser");
    modelLogDAO = (ModelLogDAO) BeanFactory.getBean("modelLogDAO");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInsertModelLog() {
    int number = modelLogDAO.insertModelLog(modelLog);
    logger.info(number);
  }

  public void testQueryModelLogsByModelName() {
    List<ModelLog> list = modelLogDAO.queryModelLogsByModelName(modelLog.getModelName());
    for (ModelLog modelLog : list) {
      logger.info(ToStringBuilder.reflectionToString(modelLog));
    }
  }

}
