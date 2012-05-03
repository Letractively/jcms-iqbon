package com.iqbon.jcms.dao.business;

import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.util.BeanFactory;

public class UserDAOTest extends TestCase {

  private static Logger logger = Logger.getLogger(UserDAOTest.class);
  UserDAO userDAO;
  protected void setUp() throws Exception {
    userDAO = (UserDAO) BeanFactory.getBean("userDAO");
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public void testInsertUser() {
    User user = new User();
    user.setUserName("testuser");
    user.setNickName("testname");
    user.setPassword("test");
    user.setPositionNum(1);
    user.setAddUser("admin");
    logger.info(userDAO.insertUser(user));
  }

  public void testDeleteUser() {
    userDAO.deleteUser("testuser");
  }
  
  public void testUserValidation() {
    User user = userDAO.validationUser("testuser", "test");
    logger.info(ToStringBuilder.reflectionToString(user));
  }
  
  public void testDeleteUserCompelete() {
    int num = userDAO.deleteUserComplete("testuser");
    logger.info(num);
  }

  public void testUpdateUserInfo() {
    User user = new User();
    user.setUserName("testuser");
    user.setNickName("testname");
    user.setPassword("test");
    user.setPositionNum(1);
    user.setAddUser("admin");
    user.setEmail("test@test");
 
    logger.info(userDAO.updateUserInfo(user));
  }
  public void testUpdaeUserLastLogin() {
    int num = userDAO
        .updaeUserLastLogin("testuser", DateFormatUtils.ISO_DATETIME_FORMAT
        .format(new Date()));
    logger.info(num);
  }
}
