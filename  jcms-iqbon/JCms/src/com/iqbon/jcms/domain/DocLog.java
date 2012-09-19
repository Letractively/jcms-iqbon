package com.iqbon.jcms.domain;

import java.util.Date;

public class DocLog {

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
