package com.iqbon.jcms.util;

public class KeyConstant {
  /**
   * session中保存验证码的KEY
   */
  public static final String SESSION_KEY_AUTH_CODE = "authcode";

  /**
   * session中保存用户信息的KEY
   */
  public static final String SESSION_KEY_USER = "jcmsUser";

  /**
   * session中保存用户复制文章的KEY
   */
  public static final String SESSION_KEY_COPY_INDEX = "copyIndex";
  
  /**
   * session 中保存用户上次复制还是剪切动作的类型
   *
   */
  public static final String SESSION_KEY_COPY_TYPE = "copyType";
  
  /**
   * cookie中保存用户名的KEY
   */
  public static final String COOKIE_KEY_USERNAME = "jcms_username";

  /**
   * 错误页面
   */
  public static final String ERROR_PAGE = "/jsp/error";

  /**
   * JSON视图
   */
  public static final String JSON_VIEW = "jsonView";

  /**
   * 后台JSP路径
   */
  public static final String ADMIN_JSP_PATH = "/jsp/admin/";
    
}
