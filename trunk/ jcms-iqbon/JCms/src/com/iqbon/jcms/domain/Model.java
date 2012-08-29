package com.iqbon.jcms.domain;

public class Model extends CMSDomain {

  private String modelName  ;
  private String content;
  private String lastModify;
  private int delete;
  private String modifyUser;
  private int type;
  private String url;
  private String title;
  private String keyword;
  private String timeout;
  private int rate;
  private String topicid;
  private String extname;
  private String addTime;
  private int status;

  public enum modelStatus {
    /**
     * 未发布
     */
    unPublish,
    /**
     * 发布
     */
    publish
  };

  public enum modelType {
    /**
     * 普通模板
     */
    normal,
    /**
     *文章模板 
     */
    doc
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getTopicid() {
    return topicid;
  }

  public void setTopicid(String topicid) {
    this.topicid = topicid;
  }

  public String getExtname() {
    return extname;
  }

  public void setExtname(String extname) {
    this.extname = extname;
  }

  public String getAddTime() {
    return addTime;
  }

  public void setAddTime(String addTime) {
    this.addTime = addTime;
  }
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

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
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
