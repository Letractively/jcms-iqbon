package com.iqbon.jcms.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.service.UserService;
import com.iqbon.jcms.util.JCMSConstant;
import com.iqbon.jcms.util.KeyConstant;

@Controller
@RequestMapping("/user")
public class UserAction {

  private Logger logger = Logger.getLogger(UserAction.class);
  private UserService userService;
  
  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  /**
   * 用户登录
   * @param username
   * @param password
   * @param authCode
   */
  @RequestMapping(value = "/login.do", method = RequestMethod.POST)
  public ModelAndView login(@RequestParam String userName, @RequestParam String password,
      @RequestParam String authCode, @RequestParam String remember, HttpServletRequest request,
      HttpServletResponse response) {
    if (logger.isDebugEnabled()) {
      logger.debug(remember);
    }
    ModelAndView view = new ModelAndView();
    if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)
        || StringUtils.isEmpty(authCode)) {
      view.setViewName(KeyConstant.ERROR_PAGE);
    } else {
      User user = userService.UserValidation(userName, password);
      if (user == null) {//用户不存在
        view.setViewName("/error.jsp");
      } else {
        HttpSession session = request.getSession();
        if (authCode.equals(session.getAttribute(KeyConstant.SESSION_KEY_AUTH_CODE))) {
          Cookie cookie = new Cookie(KeyConstant.COOKIE_KEY_USERNAME, userName);
          cookie.setMaxAge(3600 * 24 * 7);
          cookie.setPath(JCMSConstant.getHost());
          response.addCookie(cookie);//保存记住用户名的cookie
          
          session.removeAttribute(KeyConstant.SESSION_KEY_AUTH_CODE); 
          session.setAttribute(KeyConstant.SESSION_KEY_USER, user);
          view.setViewName("/admin/admin/htm");
        } else {//验证码错误
          view.setViewName(KeyConstant.ERROR_PAGE);
        }
      }      
    }
    return view;
  }
}
