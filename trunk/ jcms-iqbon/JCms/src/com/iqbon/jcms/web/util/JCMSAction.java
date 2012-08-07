package com.iqbon.jcms.web.util;

import javax.servlet.http.HttpSession;

import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.util.KeyConstant;

/**
 * actin类的父类
 * @author hp
 *
 */
public class JCMSAction {

  protected ErrorModelAndView errorMav = new ErrorModelAndView();

  /**
   * 获取错误页面的url
   * @return
   */
  public String getErrorUrl() {
    return "redirect:/admin/common/error.do";
  }

  /**
   * 从session中获取用户对象
   * @param session
   * @return
   */
  protected User getUserFromSession(HttpSession session) {
    User user = (User) session.getAttribute(KeyConstant.SESSION_KEY_USER);
    return user;
  }
}
