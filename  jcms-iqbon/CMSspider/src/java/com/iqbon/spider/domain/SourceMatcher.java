package com.iqbon.spider.domain;

import com.google.code.morphia.annotations.Embedded;

@Embedded()
public class SourceMatcher {

  private String linkParent;

  private String linkMatcher;

  private String description;

  public String getLinkMatcher() {
    return linkMatcher;
  }

  public void setLinkMatcher(String linkMatcher) {
    this.linkMatcher = linkMatcher;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLinkParent() {
    return linkParent;
  }

  public void setLinkParent(String linkParent) {
    this.linkParent = linkParent;
  }

}
