package com.iqbon.jcms.domain;

public class PushRecord extends CMSDomain {

   /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int indexid;
   
   private int docid;
   
   private String modelName;
   
   private String title;
   
   private int lspri;
   
   private String url;
   
   private String subTile;
   
   private String addDate;
   
   private String lastModify;
   
   private String modifyUser;
   
   private String topicid;
   
   private String img;

  public int getIndexid() {
    return indexid;
  }

  public void setIndexid(int indexid) {
    this.indexid = indexid;
  }

  public int getDocid() {
    return docid;
  }

  public void setDocid(int docid) {
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

  public String getSubTile() {
    return subTile;
  }

  public void setSubTile(String subTile) {
    this.subTile = subTile;
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
}
