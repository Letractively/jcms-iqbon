package com.iqbon.jcms.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * JCMS的全局变量
 * @author hp
 *
 */
public class JCMSConstant {
  

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

  /**
   * 把日期按照yyyy-MM-dd hh:mm:ss格式转成字符串
   * @param date
   * @return
   */
  public static String getDateString(Date date) {
    return DateFormatUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss");
  }

  /**
   * 生成模板的URL
   * @param modelName
   * @param suffix
   * @return
   */
  public static String createModelUrl(String modelName, String suffix) {
    JCMSProperties propertiy= JCMSProperties.getInstance();
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DATE);
    StringBuffer sb = new StringBuffer();
    return sb.append(propertiy.getHost()).append("/m/").append(year).append("/").append(month)
        .append("/")
        .append(day).append(modelName).append(suffix).toString();
  }
  
  /**
   * 根据输出页面的地址返回输出页面的路径
   * @param url
   * @return
   */
  public static File createModelOutputFile(String url) {
    if (StringUtils.isEmpty(url)) {
      return null;
    }
    JCMSProperties propertiy = JCMSProperties.getInstance();
    String path = propertiy.getOutFilePath();
    String host = propertiy.getHost();
    String fileURI = path + url.replace(host, "");
    return new File(fileURI);
  }
}
