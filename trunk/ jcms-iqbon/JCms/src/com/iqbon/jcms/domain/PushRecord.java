package com.iqbon.jcms.domain;

public class PushRecord extends CMSDomain {

   /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 默认权限
   */
  public static final int DEFAULT_LSPRI = 60;

  /**
   * 推送记录类型
   */
  public static enum PUSH_RECORD_TYPE {
    emptyDoc, doc, model
  };

  private String indexid;
   
  private String docid;
   
   private String modelName;
   
   private String title;
   
   private int lspri;
   
   private String url;
   
   private String subTitle;
   
   private String addDate;
   
   private String lastModify;
   
   private String modifyUser;
   
   private String topicid;
   
   private String img;
   
   private int type;

  public String getIndexid() {
    return indexid;
  }

  public void setIndexid(String indexid) {
    this.indexid = indexid;
  }

  public String getDocid() {
    return docid;
  }

  public void setDocid(String docid) {
    this.docid = docid;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getLspri() {
    return lspri;
  }

  public void setLspri(int lspri) {
    this.lspri = lspri;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSubTitle() {
    return subTitle;
  }

  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }

  public String getAddDate() {
    return addDate;
  }

  public void setAddDate(String addDate) {
    this.addDate = addDate;
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

  public String getTopicid() {
    return topicid;
  }

  public void setTopicid(String topicid) {
    this.topicid = topicid;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}
