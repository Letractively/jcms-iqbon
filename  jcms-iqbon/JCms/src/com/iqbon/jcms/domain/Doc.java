package com.iqbon.jcms.domain;

public class Doc extends CMSDomain {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int docid;

  private String title;
  
  private String content;
  
  private String digest;
  
  private String modifyUser;
  
  private int delete;
  
  private String reporter;
  
  private String lastModify;
  
  private String url;
  
  private int type;
  
  private String keyword;

  public int getDocid() {
    return docid;
  }

  public void setDocid(int docid) {
    this.docid = docid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDigest() {
    return digest;
  }

  public void setDigest(String digest) {
    this.digest = digest;
  }

  public String getModifyUser() {
    return modifyUser;
  }

  public void setModifyUser(String modifyUser) {
    this.modifyUser = modifyUser;
  }

  public int getDelete() {
    return delete;
  }

  public void setDelete(int delete) {
    this.delete = delete;
  }

  public String getReporter() {
    return reporter;
  }

  public void setReporter(String reporter) {
    this.reporter = reporter;
  }

  public String getLastModify() {
    return lastModify;
  }

  public void setLastModify(String lastModify) {
    this.lastModify = lastModify;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
