package com.iqbon.jcms.dao.business;

import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.util.BeanFactory;

public class ModelDAOTest extends TestCase {

  private final Logger logger = Logger.getLogger(ModelDAOTest.class);
  private Model model = new Model();
  private ModelDAO modelDAO;
  protected void setUp() throws Exception {
    super.setUp();
    modelDAO = (ModelDAO) BeanFactory.getBean("modelDAO");
    model.setContent("test");
    model.setDelete(0);
    model.setExtname(".html");
    model.setModelName("testModel");
    model.setModifyUser("testuser");
    model.setRate(10);
    model.setTimeout("2013-01-02");
    model.setTitle("标题");
    model.setTopicid("134523");
    model.setType(0);
    model.setUrl("http:blog.iqbon.com");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInsertModel() {
    int number = modelDAO.insertModel(model);
    logger.info(number);
  }

  public void testInsertModelRefresh() {
    int number = modelDAO.insertModelRefresh(model);
    logger.info(number);
  }

  public void testQueryModelNumberByTopic() {
    int number = modelDAO.queryModelNumberByTopic(model.getTopicid());
    logger.info(number);
  }

  public void testQueryModelByTopic() {
    List<Model> list = modelDAO.queryModelByTopic(model.getTopicid());
    for (Model model : list) {
      logger.info(ToStringBuilder.reflectionToString(model));
    }
  }

  public void testQueryModelByModelName() {
    Model model = modelDAO.queryModelByModelName("afawrwrwwerw");
    logger.info(ToStringBuilder.reflectionToString(model));
  }

  public void testDeleteModel() {
    int number = modelDAO.deleteModel(model.getModelName());
    logger.info(number);
  }

  public void testupdateModel() {
    int number = modelDAO.updateModel(model);
    logger.info(number);
  }

}
