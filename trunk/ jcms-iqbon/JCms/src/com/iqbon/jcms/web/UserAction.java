package com.iqbon.jcms.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.service.UserService;
import com.iqbon.jcms.util.JCMSProperties;
import com.iqbon.jcms.util.KeyConstant;
import com.iqbon.jcms.web.util.JCMSAction;

@Controller
@Scope("prototype")
@RequestMapping("/admin/user")
public class UserAction extends JCMSAction {

  private Logger logger = Logger.getLogger(UserAction.class);
  @Autowired
  private UserService userService;

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
    User user = userService.userValidation(userName, password);
    if (user == null) {//用户不存在
        view.setViewName(KeyConstant.ERROR_PAGE);
      } else {
      JCMSProperties propertiy = JCMSProperties.getInstance();
        HttpSession session = request.getSession();
        if (authCode.equals(session.getAttribute(KeyConstant.SESSION_KEY_AUTH_CODE))) {
        if (BooleanUtils.isTrue(remember)) {//保存记住用户名的cookie
            Cookie cookie = new Cookie(KeyConstant.COOKIE_KEY_USERNAME, userName);
            cookie.setMaxAge(3600 * 24 * 7);
          cookie.setPath(propertiy.getHost());
            response.addCookie(cookie);
          }
          session.removeAttribute(KeyConstant.SESSION_KEY_AUTH_CODE); 
          session.setAttribute(KeyConstant.SESSION_KEY_USER, user);
          view.setViewName("redirect:/admin/common/index.do");
        logger.info("user:" + user.getUserName() + " 登录");
      } else {//验证码错误
        errorMav.setErrorInfo("验证码错误");
        logger.warn("user:" + user.getUserName() + "验证码错误");
        return errorMav;
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

  /**
   * 根据用户名获取用户信息
   * @param userName
   * @return
   */
  @RequestMapping(value = "/userInfo.do")
  public ModelAndView getUserInfoByUserName(@RequestParam("userName")
  String userName) {
    ModelAndView view = new ModelAndView();
    User user = userService.getUserInfoByUserName(userName);
    view.addObject("user", user);
    view.setViewName(KeyConstant.ADMIN_JSP_PATH + "user");
    return view;
  }
  
  /**
   * 修改用户信息
   * @param userName
   * @param email
   * @param telephone
   * @param mobile
   * @return
   */
  @RequestMapping(value = "/modifyUserInfo.do")
  public ModelAndView modifyUserInfo(@RequestParam("userName")
  String userName, @RequestParam("nickname")
  String nickname, @RequestParam(value = "email", required = false)
  String email, @RequestParam(value = "telephone", required = false)
  String telephone, @RequestParam(value = "mobile", required = false)
  String mobile) {

    User user = new User();
    user.setUserName(userName);
    user.setNickName(nickname);
    user.setEmail(email);
    user.setTelephone(telephone);
    user.setMobile(mobile);
    int number = userService.modifyUserInfo(user);
    if (number > 0) {
      ModelAndView view = new ModelAndView();
      view.addObject("user", user);
      view.setViewName(KeyConstant.ADMIN_JSP_PATH + "user");
      return view;
    } else {
      return errorMav;
    }
  }

  /**
   * 修改用户密码
   * @param userName
   * @param password
   * @return
   */
  @RequestMapping(value = "/modifyUserPassword.do")
  public ModelAndView updatePassword(@RequestParam("userName")
  String userName, @RequestParam("password")
  String password, @RequestParam("oldPassword")
  String oldPassword) {
    User user = userService.updatePasswordByUserName(userName, password, oldPassword);
    if (null == user) {
      return errorMav;
    } else {
      ModelAndView view = new ModelAndView();
      view.addObject("userName", user.getUserName());
      view.setViewName(KeyConstant.ADMIN_JSP_PATH + "updatePassword");
      return view;
    }
  }

  /**
   * 显示修改用户密码的页面
   * @param userName
   * @return
   */
  @RequestMapping(value = "/showModifyUserPassword.do")
  public ModelAndView showUpdatePassword(@RequestParam("userName")
  String userName) {
    ModelAndView view = new ModelAndView();
    view.addObject("userName", userName);
    view.setViewName(KeyConstant.ADMIN_JSP_PATH + "updatePassword");
    return view;
  }

  /**
   * 退出登录
   * @param username
   * @return
   */
  @RequestMapping("/logout.do")
  public String logout(@RequestParam("userName")
  String username, HttpServletRequest request) {
    HttpSession session = request.getSession();
    String url = "redirect:/login.htm";
    if (StringUtils.isEmpty(username) || session.getAttribute(KeyConstant.SESSION_KEY_USER) == null) {
      return url;
    } else {
      session.removeAttribute(KeyConstant.SESSION_KEY_USER);
      return url;
    }
  }
}
