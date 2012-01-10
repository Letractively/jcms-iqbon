package com.iqbon.jcms.dao.business;

import junit.framework.TestCase;

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

}
