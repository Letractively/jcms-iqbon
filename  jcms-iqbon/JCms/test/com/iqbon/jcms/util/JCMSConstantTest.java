package com.iqbon.jcms.util;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Doc;

public class JCMSConstantTest extends TestCase {

  private static final Logger logger = Logger.getLogger(JCMSConstantTest.class);

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testCreateModelUrl() {
    String url = JCMSConstant.createModelUrl("test", ".html");
    logger.info(url);
  }

  public void testCreateDocUrl() {
    String url = JCMSConstant.createDocUrl("test", Doc.docType.normal.ordinal());
    logger.info(url);
  }

}
