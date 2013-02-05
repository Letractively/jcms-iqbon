package com.iqbon.spider.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class BaseController {

  public BaseController() {

  }
  
  /**
   * 从request中获取字符串参数
   * @param request
   * @param name
   * @param defaultString
   * @return
   */
  public String getStringParameter(HttpServletRequest request,String name,String defaultString) {
    if(StringUtils.isEmpty(name)){
      return null;
    } else if (StringUtils.isEmpty(request.getParameter(name))) {
      return defaultString;
    } else {
      return request.getParameter(name);
    }
  }

  /**
   * 从request对象中获取int参数
   * @param request
   * @param name
   * @param defaultNum
   * @return
   */
  public int getIntParameter(HttpServletRequest request, String name, int defaultNum) {
    if (StringUtils.isEmpty(name) || StringUtils.isEmpty(request.getParameter(name))) {
      return defaultNum;
    }  else {
      return NumberUtils.createInteger(request.getParameter(name)).intValue();
    }
  }
}
