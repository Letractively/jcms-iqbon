package com.iqbon.jcms.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.iqbon.jcms.domain.Doc;

/**
 * JCMS的全局变量
 * @author hp
 *
 */
public class JCMSConstant {
  
  private static JCMSProperties propertiy = JCMSProperties.getInstance();

  private static String MODEL_FOLDER = "/m/";

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
   * 生成一个pushRecordId
   * @return
   */
  public static String createPushRecordId() {
    Date date = new Date();
    String dateStr = DateFormatUtils.format(date, "yyMMddHHmmssss");
    String random = RandomStringUtils.randomAlphanumeric(4);
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
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DATE);
    StringBuffer sb = new StringBuffer();
    return sb.append(propertiy.getHost()).append(MODEL_FOLDER).append(year).append("/")
        .append(month)
        .append("/").append(day).append("/").append(modelName).append(suffix).toString();
  }
  
  /**
   * 重新更新模板的URL地址
   * @param url
   * @return
   */
  public static String refreshModelUrl(String url, String suffix) {
    StringBuffer sb = new StringBuffer();
    sb.append(propertiy.getHost()).append(MODEL_FOLDER)
        .append(url.split(MODEL_FOLDER)[1].split("\\.")[0]).append(suffix);
    return sb.toString();
  }

  /**
   * 生成文章的URL
   * @param docid
   * @param type
   * @return
   */
  public static String createDocUrl(String docid, int type) {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DATE);
    String docType = "doc";
    if(type==Doc.docType.picture.ordinal()){
      docType = "pic";
    }else if(type==Doc.docType.video.ordinal()){
      docType = "vid";
    }
    StringBuffer sb = new StringBuffer();
    return sb.append(propertiy.getHost()).append("/").append(docType).append("/").append(year)
        .append("/").append(month).append("/").append(day).append("/").append(docid)
        .append(".html").toString();
  }

  /**
   * 根据模板输出页面的url返回输出页面的路径
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

  /**
   * 根据文章url返回输出文章页面的路径
   * @param url
   * @return
   */
  public static File createDocOutputFile(String url) {
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
