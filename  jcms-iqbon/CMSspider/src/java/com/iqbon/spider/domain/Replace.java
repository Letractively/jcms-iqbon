package com.iqbon.spider.domain;

import com.google.code.morphia.annotations.Embedded;

@Embedded()
public class Replace {

  private String matcher;

  private String replacement;

  public String getMatcher() {
    return matcher;
  }

  public void setMatcher(String matcher) {
    this.matcher = matcher;
  }

  public String getReplacement() {
    return replacement;
  }

  public void setReplacement(String replacement) {
    this.replacement = replacement;
  }

}
