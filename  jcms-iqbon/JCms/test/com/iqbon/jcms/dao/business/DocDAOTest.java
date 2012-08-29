package com.iqbon.jcms.dao.business;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Doc;
import com.iqbon.jcms.util.BeanFactory;

public class DocDAOTest extends TestCase {

  private final Logger logger = Logger.getLogger(DocDAOTest.class);

  private Doc doc = new Doc();

  private DocDAO docDAO;

  protected void setUp() throws Exception {
    super.setUp();
    docDAO = (DocDAO) BeanFactory.getBean("docDAO");
    doc.setContent("content");
    doc.setDelete(0);
    doc.setDigest("描述");
    doc.setKeyword("keyword");
    doc.setModifyUser("testuser");
    doc.setReporter("testuser");
    doc.setTitle("title");
    doc.setType(0);
    doc.setUrl("http://blog.iqbon.com");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInsertDoc() {
    int number = docDAO.insertDoc(doc);
    logger.info(number);
  }

  public void testQueryDocById() {
    Doc one = docDAO.queryDocById(1);
    logger.info(ToStringBuilder.reflectionToString(one));
  }

  public void testUpdateDoc() {
    doc.setDocid(1);
    doc.setDelete(1);
    int number = docDAO.updateDoc(doc);
    logger.info(number);
  }

}
