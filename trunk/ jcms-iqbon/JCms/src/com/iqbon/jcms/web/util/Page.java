package com.iqbon.jcms.web.util;

/**
 * 分页公用类
 * @author hp
 *
 */
public class Page {
  
  @SuppressWarnings("unused")
  private Page() {
  }
  
  public Page(int totalNum,int currentPage) {
    this.totalNum = totalNum;
    this.currentPage = currentPage;
    countTotalPageNum();
    countNextAndPrePage();
  }
  

  /**
   * 默认的每页显示条数
   */
  private int pageSize = 20;

  private int totalNum;

  private int nextPage;

  private int prePage;

  private int currentPage = 1;

  private int totalPage = 1;

  /**
   * 计算总页数
   * @param totalNum
   * @param pageSize
   * @return
   */
  private void countTotalPageNum() {
    if (pageSize <= 0 || totalNum == 0) {
      totalPage  = 1;
    } else {
      if (totalNum % pageSize > 0) {
        totalPage= (totalNum / pageSize) + 1;
      } else {
        totalPage=( totalNum / pageSize);
      }
    }
  }
  
  /**
   * 计算上一页和下一页
   */
  private void countNextAndPrePage() {
    if (currentPage > 1) {
      prePage = currentPage - 1;
    }else{
      prePage = 1;
    }
    if (totalPage > currentPage) {
      nextPage = currentPage + 1;
    } else {
      nextPage = currentPage;
    }
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    if (pageSize > 0) {
      this.pageSize = pageSize;
    }
    countTotalPageNum();
  }

  public int getTotalNum() {
    return totalNum;
  }

  public int getNextPage() {
    return nextPage;
  }

  public int getPrePage() {
    return prePage;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public int getTotalPage() {
    return totalPage;
  }

}
