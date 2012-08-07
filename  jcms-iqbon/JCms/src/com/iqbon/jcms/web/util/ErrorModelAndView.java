package com.iqbon.jcms.web.util;

import org.springframework.web.servlet.ModelAndView;

import com.iqbon.jcms.util.KeyConstant;

/**
 * JCMS错误页面的mav
 * @author hp
 *
 */
public class ErrorModelAndView extends ModelAndView {

  public ErrorModelAndView() {
    this.setViewName(KeyConstant.ERROR_PAGE);
  }

  /**
   * 设置错误信息
   * @param errorInfo
   */
  public void setErrorInfo(String errorInfo) {
    this.addObject("errorInfo", errorInfo);
  }

  /**
   * 设置跳转地址
   * @param url
   */
  public void setReddirectUrl(String url) {
    this.addObject("redirect", url);
  }
}
