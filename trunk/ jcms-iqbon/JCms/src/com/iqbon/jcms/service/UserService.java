package com.iqbon.jcms.service;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iqbon.jcms.dao.business.UserDAO;
import com.iqbon.jcms.domain.User;

@Service
public class UserService {
  
  @Autowired
  private UserDAO userDAO;

  /**
   * 根据用户名和密码验证用户是否存在，存在则返回用户相关信息<br/>
   * 并更新用户最后登录时间
   * @param user
   * @param password
   * @return
   */
  public User userValidation(String userName, String password) {
    User user = userDAO.validationUser(userName, password);
    if (user != null) {
      String lastLogin = DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date());
      userDAO.updaeUserLastLogin(userName, lastLogin);
    }
    return user;
  }

  /**
   * 更新用户信息
   * @param user
   * @return
   */
  public int modifyUserInfo(User user) {
    return userDAO.updateUserInfo(user);
  }
  
  /**
   * 根据用户名获取用户信息
   * @param userName
   * @return
   */
  public User getUserInfoByUserName(String userName) {
    return userDAO.queryUserInfoByUserName(userName);
  }
}
