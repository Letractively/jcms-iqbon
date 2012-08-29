package com.iqbon.jcms.util;

import org.apache.velocity.VelocityContext;

/**
 * JCMS的velocityContext
 * @author hp
 *
 */
public class JCMSVelocityContext extends VelocityContext {

  private static JCMSVelocityContext jCMSVelocityContext;
  
  private JCMSVelocityContext() {
    // TODO Auto-generated constructor stub
  }
  
  /**
   * 获取实例
   * @return
   */
  public static JCMSVelocityContext getInstance() {
    if (null == jCMSVelocityContext) {
      jCMSVelocityContext = new JCMSVelocityContext();
      return jCMSVelocityContext;
    } else {
      return jCMSVelocityContext;
    }
  }
  
}
