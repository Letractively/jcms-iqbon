package com.iqbon.jcms.web.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.util.KeyConstant;

/**
 * actin类的父类
 * @author hp
 *
 */
public class JCMSAction {

  protected ModelAndView errorMav = new ModelAndView();

  public JCMSAction() {
    errorMav.setViewName(KeyConstant.ERROR_PAGE);
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
