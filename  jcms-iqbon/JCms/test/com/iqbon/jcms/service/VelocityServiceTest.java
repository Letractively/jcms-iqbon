package com.iqbon.jcms.service;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.iqbon.jcms.util.BeanFactory;

public class VelocityServiceTest extends TestCase {

  private static final Logger logger = Logger.getLogger(VelocityServiceTest.class);

  private VelocityService velocityService;

  protected void setUp() throws Exception {
    super.setUp();
    velocityService = (VelocityService) BeanFactory.getBean("velocityService");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testParse() {
    String content = "hello world $name";
    String result = velocityService.parse(content);
    logger.info(result);
  }

}
