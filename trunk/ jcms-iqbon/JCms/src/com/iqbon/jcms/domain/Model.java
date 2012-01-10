package com.iqbon.jcms.domain;

public class Model extends CMSDomain {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String modelName  ;
  private String content;
  private String lastModify;
  private int delete;
  private String modifyUser;
  private int type;
  private String url;
  private String title;
  private String keword;
  private String timeout;
  private int rate;
  public String getModelName() {
    return modelName;
  }
  public void setModelName(String modelName) {
    this.modelName = modelName;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getLastModify() {
    return lastModify;
  }
  public void setLastModify(String lastModify) {
    this.lastModify = lastModify;
  }
  public int getDelete() {
    return delete;
  }
  public void setDelete(int delete) {
    this.delete = delete;
  }
  public String getModifyUser() {
    return modifyUser;
  }
  public void setModifyUser(String modifyUser) {
    this.modifyUser = modifyUser;
  }
  public int getType() {
    return type;
  }
  public void setType(int type) {
    this.type = type;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getKeword() {
    return keword;
  }
  public void setKeword(String keword) {
    this.keword = keword;
  }
  public String getTimeout() {
    return timeout;
  }

  public void setTimeout(String timeout) {
    this.timeout = timeout;
  }

  public int getRate() {
    return rate;
  }

  public void setRate(int rate) {
    this.rate = rate;
  }
}
