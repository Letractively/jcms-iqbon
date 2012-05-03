package com.iqbon.jcms.web.util;

/**
 * action的公用工具类
 * @author hp
 *
 */
public class ActionUtil {

  /**
   * 默认的每页显示条数
   */
  public static final int DEFAUL_PAGE_SIZE = 20;

  /**
   * 计算总页数
   * @param totalNum
   * @param pageSize
   * @return
   */
  public static int countTotalPageNum(int totalNum, int pageSize) {
    if (pageSize <= 0) {
      return 0;
    } else {
      if (totalNum % pageSize > 0) {
        return (totalNum / pageSize) + 1;
      } else {
        return totalNum / pageSize;
      }
    }
  }
  
}
