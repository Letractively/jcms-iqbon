package com.iqbon.jcms.domain;

public class Topic extends CMSDomain {
  
  private String topicId;
  
  private String topicName;
  
  private String lastModify;
  
  private String modifyUser;
  
  private String topicTree;

  private String parentTopic;

  public String getParentTopic() {
    return parentTopic;
  }

  public void setParentTopic(String parentTopic) {
    this.parentTopic = parentTopic;
  }

  public String getTopicId() {
    return topicId;
  }

  public void setTopicId(String topicId) {
    this.topicId = topicId;
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public String getLastModify() {
    return lastModify;
  }

  public void setLastModify(String lastModify) {
    this.lastModify = lastModify;
  }

  public String getModifyUser() {
    return modifyUser;
  }

  public void setModifyUser(String modifyUser) {
    this.modifyUser = modifyUser;
  }

  public String getTopicTree() {
    return topicTree;
  }

  public void setTopicTree(String topicTree) {
    this.topicTree = topicTree;
  }
  }
