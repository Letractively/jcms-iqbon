package com.iqbon.jcms.service;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.Model;
import com.iqbon.jcms.util.BeanFactory;

public class ModelServiceTest extends TestCase {

  private static final Logger logger = Logger.getLogger(ModelServiceTest.class);

  ModelService modelService;
  
  Model model;

  protected void setUp() throws Exception {
    modelService = (ModelService) BeanFactory.getBean("modelService");
    model = new Model();
    model.setUrl("http://localhost:8080/m/2012/8/8afwerwr.html");
    model.setContent("hello world");
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testPublishModelContent() {
    try {
      modelService.publishModelContent(model);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
