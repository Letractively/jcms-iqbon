package com.iqbon.jcms.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
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
@RequestMapping("/admin/user")
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
  public ModelAndView login(@RequestParam("userName") String userName,
      @RequestParam("password") String password, @RequestParam("authCode") String authCode,
      @RequestParam(value = "remember", required = false) boolean remember,
      HttpServletRequest request,
      HttpServletResponse response) {
    ModelAndView view = new ModelAndView();
    if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)
        || StringUtils.isEmpty(authCode)) {
      view.setViewName(KeyConstant.ERROR_PAGE);
    } else {
      User user = userService.UserValidation(userName, password);
      if (user == null) {//用户不存在
        view.setViewName(KeyConstant.ERROR_PAGE);
      } else {
        HttpSession session = request.getSession();
        if (authCode.equals(session.getAttribute(KeyConstant.SESSION_KEY_AUTH_CODE))) {
          if (BooleanUtils.isTrue(remember)) {//保存记住用户名的cookie
            Cookie cookie = new Cookie(KeyConstant.COOKIE_KEY_USERNAME, userName);
            cookie.setMaxAge(3600 * 24 * 7);
            cookie.setPath(JCMSConstant.getHost());
            response.addCookie(cookie);
          }
          session.removeAttribute(KeyConstant.SESSION_KEY_AUTH_CODE); 
          session.setAttribute(KeyConstant.SESSION_KEY_USER, user);
          view.setViewName("redirect:/admin/common/index.do");
          logger.info("user:" + user.getUserName() + "login");
        } else {//验证码错误
          view.setViewName(KeyConstant.ERROR_PAGE);
        }
      }      
    }
    return view;
  }

  /**
   * 从session中获取用户信息
   * @return
   */
  @RequestMapping(value = "/userSessionInfo.do")
  public ModelAndView getUserInfoFromSession(HttpSession session) {
    ModelAndView view = new ModelAndView();
    view.setViewName(KeyConstant.ADMIN_JSP_PATH + "top");
    view.addObject("user", session.getAttribute(KeyConstant.SESSION_KEY_USER));
    return view;
  }
}
