package com.iqbon.jcms.service;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.util.BeanFactory;

public class UserServiceTest extends TestCase {

  private static Logger logger = Logger.getLogger(UserService.class);
  private UserService userService;
  
  protected void setUp() throws Exception {
    userService = (UserService) BeanFactory.getBean("userService");
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testUserValidation() {
    User user = userService.UserValidation("testuser", "test");
    logger.info(ToStringBuilder.reflectionToString(user));
  }

}
