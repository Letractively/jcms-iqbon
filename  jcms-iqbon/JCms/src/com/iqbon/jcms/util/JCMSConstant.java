package com.iqbon.jcms.util;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * JCMS的全局变量
 * @author hp
 *
 */
public class JCMSConstant {
  
  private static String host = "/";

  /**
   * 获取站点域名
   * @return
   */
  public static String getHost() {
    return host;
  }

  /**
   * 推送记录类型
   */
  public static enum PUSH_RECORD_TYPE {
    emptyDoc, doc, model
  };

  /**
   * 生成一个topicID
   * @return
   */
  public static String createTopicId() {
    Date date = new Date();
    String dateStr = DateFormatUtils.format(date, "yyMMddHHmmssssss");
    String random = RandomStringUtils.randomAlphanumeric(2);
    return dateStr + random;
  }
}
