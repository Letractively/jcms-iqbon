package com.iqbon.jcms.web.util;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.util.KeyConstant;

@Controller
@Scope("prototype")
@RequestMapping("/admin/common")
public class UrlRedirectAction {
  
  private final Logger logger = Logger.getLogger(UrlRedirectAction.class);

  /**
   * 后台左边菜单跳转
   * @return
   */
  @RequestMapping("/leftMenu.do")
  public ModelAndView LeftMenuRedirect() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName(KeyConstant.ADMIN_JSP_PATH + "left");
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
  public ModelAndView errorPage() {
    ModelAndView errorMav = new ModelAndView();
    errorMav.setViewName(KeyConstant.ERROR_PAGE);
    return errorMav;
  }
}
