package com.iqbon.jcms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.domain.User;
import com.iqbon.jcms.service.UserService;
import com.iqbon.jcms.util.KeyConstant;

@Controller
@RequestMapping("/user")
public class UserAction {

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
  public ModelAndView login(@PathVariable String userName, @PathVariable String password,
      @PathVariable String authCode, @PathVariable String remember, HttpServletRequest request) {
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
          
          session.removeAttribute(KeyConstant.SESSION_KEY_AUTH_CODE);
          view.setViewName("/admin/admin/htm");
        } else {//验证码错误
          view.setViewName(KeyConstant.ERROR_PAGE);
        }
      }      
    }
    return view;
  }
}
