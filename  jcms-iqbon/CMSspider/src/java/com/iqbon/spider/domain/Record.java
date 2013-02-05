package com.iqbon.spider.domain;

import java.util.Date;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;

@Entity(value = "record", noClassnameStored = true)
public class Record {

  @Id
  private ObjectId id;

  @Indexed(name = "recordUrl", unique = true)
  private String url;

  private String content;

  private String sourceId;
  
  private Date lastModify;

  private String title;

  @Indexed(value = IndexDirection.ASC, name = "recordReport")
  private boolean reported;

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSourceId() {
    return sourceId;
  }

  public void setSourceId(String sourceId) {
    this.sourceId = sourceId;
  }

  public Date getLastModify() {
    return lastModify;
  }

  public void setLastModify(Date lastModify) {
    this.lastModify = lastModify;
  }

  public boolean isReported() {
    return reported;
  }

  public void setReported(boolean reported) {
    this.reported = reported;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Record(String title, String url, String content, String sourceId) {
    this.title = title;
    this.url = url;
    this.content = content;
    this.sourceId = sourceId;
  }

  public Record() {
    // TODO Auto-generated constructor stub
  }
}
