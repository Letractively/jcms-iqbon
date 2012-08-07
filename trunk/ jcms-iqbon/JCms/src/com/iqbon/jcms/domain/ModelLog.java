package com.iqbon.jcms.domain;

/**
 * 模板操作日志
 * @author hp
 *
 */
public class ModelLog extends CMSDomain {

  public static final String MODEL_ADD_COMMON = " 创建了模板";
  public static final String MODEL_MODIFY_COMMON = " 修改了模板";
  public static final String MODEL_DELETE_COMMON = "删除了模板";
  public static final String MODEL_PUBLISH_COMMON = "发布了模板";

  private String modelName;
  private String userName;
  private String time;
  private String content;
  private String modelContent;
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getModelContent() {
    return modelContent;
  }

  public void setModelContent(String modelContent) {
    this.modelContent = modelContent;
  }

}
