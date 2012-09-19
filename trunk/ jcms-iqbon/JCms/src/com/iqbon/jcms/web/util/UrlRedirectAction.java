package com.iqbon.jcms.web.util;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.util.KeyConstant;

@Controller
@Scope("prototype")
@RequestMapping("/admin/common")
public class UrlRedirectAction {
  
  private final Logger logger = Logger.getLogger(UrlRedirectAction.class);

  /**
   * 后台左边内容管理菜单跳转
   * @return
   */
  @RequestMapping("/leftContentManager.do")
  public ModelAndView LeftContentMenuRedirect() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName(KeyConstant.ADMIN_JSP_PATH + "leftContentManager");
    return modelAndView;
  }

  /**
   * 后台左边个人设置菜单跳转
   * @return
   */
  @RequestMapping("/leftUserSetting.do")
  public ModelAndView LeftUserMenuRedirect(@RequestParam("userName")
  String userName) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("userName", userName);
    modelAndView.setViewName(KeyConstant.ADMIN_JSP_PATH + "leftUserSetting");
    return modelAndView;
  }

  /**
   * 后台首页跳转
   * @return
   */
  @RequestMapping("/index.do")
  public ModelAndView adminMainPage(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName(KeyConstant.ADMIN_JSP_PATH + "admin");
    return modelAndView;
  }

  /**
   * 错误页
   * @return
   */
  @RequestMapping("/error.do")
  public ModelAndView errorPage(@RequestParam(value = "errorInfo", required = false)
  String errorInfo) {
    ModelAndView errorMav = new ModelAndView();
    errorMav.addObject("errorInfo", errorInfo);
    errorMav.setViewName(KeyConstant.ERROR_PAGE);
    return errorMav;
  }
}
