package com.iqbon.spider.domain;

import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;

@Entity(value = "source", noClassnameStored = true)
public class Source {

  @Id
  private ObjectId id;

  @Indexed(name = "sourceUrl", unique = true)
  private String url;

  private String description;
  
  private String contentMatcher;

  @Embedded
  private List<SourceMatcher> matchers;

  @Embedded
  private List<Replace> replaces;

  private String nextPage;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<SourceMatcher> getMatchers() {
    return matchers;
  }

  public void setMatchers(List<SourceMatcher> matchers) {
    this.matchers = matchers;
  }

  public String getContentMatcher() {
    return contentMatcher;
  }

  public void setContentMatcher(String contentMatcher) {
    this.contentMatcher = contentMatcher;
  }

  public List<Replace> getReplaces() {
    return replaces;
  }

  public void setReplaces(List<Replace> replaces) {
    this.replaces = replaces;
  }

  public String getNextPage() {
    return nextPage;
  }

  public void setNextPage(String nextPage) {
    this.nextPage = nextPage;
  }

}
