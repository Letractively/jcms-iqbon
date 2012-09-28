package com.iqbon.jcms.domain;

import java.util.Date;

public class DocLog {

  /**
   * 文章增加的日志描述
   */
  public static final String DOC_ADD_COMMON = " 新建了文章";
  /**
   * 文章修改的日志描述
   */
  public static final String DOC_MODIFY_COMMON = " 修改了文章";
  /**
   * 文章删除的日志描述
   */
  public static final String DOC_DELETE_COMMON = "删除了文章";
  /**
   * 文章发布的日志描述
   */
  public static final String DOC_PUBLISH_COMMON = "发布了文章";

  private String docid;

  private String userName;

  private Date time;

  private String content;

  public String getDocid() {
    return docid;
  }

  public void setDocid(String docid) {
    this.docid = docid;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
