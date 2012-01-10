package com.iqbon.jcms.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean的通用获取类
 * @author hp
 *
 */
public class BeanFactory {

  /**
   * bean的所有配置文件
   */
  public static final String[] BEAN_CONFIG = { "classpath:applicationContext-resources.xml",
      "classpath:applicationContext-business.xml" };
  
  private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
      BEAN_CONFIG);

  /**
   * 根据名字获取Bean
   * @param name
   * @return
   */
  public static Object getBean(String name) {
    return applicationContext.getBean(name);
  }
}
