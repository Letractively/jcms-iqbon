package com.iqbon.spider.util;

import org.apache.commons.validator.routines.UrlValidator;

public class HttpConstant {
  /**
   * Http请求返回正常状态码
   */
  public static final int HTTP_STATUS_OK = 200;

  public final static String HREF = "href";
  public final static String A = "a";
  public final static UrlValidator URL_VALIDATOR = UrlValidator.getInstance();
}
