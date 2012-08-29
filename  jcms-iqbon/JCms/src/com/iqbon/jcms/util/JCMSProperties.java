package com.iqbon.jcms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class JCMSProperties {

  private static JCMSProperties jcmsProperties = new JCMSProperties();

  private Logger logger = Logger.getLogger(JCMSProperties.class);

  /**
   * 系统域名
   */
  private String host;

  /**
   * 输出页面编码
   */
  private String outFileCoding;

  /**
   * 输出页面路径
   */
  private String outFilePath;

  private JCMSProperties() {
    try {
      init();
    } catch (IOException e) {
      logger.error("加载jcms.properties出错", e);
    }
  }

  public static JCMSProperties getInstance() {
    if (jcmsProperties == null) {
      return new JCMSProperties();
    } else {
      return jcmsProperties;
    }
  }

  /**
   * 初始化，加载系统参数
   * @throws IOException
   */
  private void init() throws IOException {
    Properties props = new Properties();
    InputStream is = this.getClass().getClassLoader().getResourceAsStream("jcms.properties");
    props.load(is);
    this.host = props.getProperty("jcms.host");
    this.outFileCoding = props.getProperty("jcms.outfile.coding");
    this.outFilePath = props.getProperty("jcms.outfile.path");
  }

  public String getHost() {
    return host;
  }

  public String getOutFileCoding() {
    return outFileCoding;
  }

  public String getOutFilePath() {
    return outFilePath;
  }

}
