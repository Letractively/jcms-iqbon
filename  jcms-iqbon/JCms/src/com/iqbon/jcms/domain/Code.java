package com.iqbon.jcms.domain;

/**
 * 系统码表实体类
 * @author hp
 *
 */
public class Code extends CMSDomain {

  /**
   * 
   */
  private static final long serialVersionUID = -692034630061203867L;
  
  private String groupName;
  private String key;
  private String value;
  private String parentKey;
  
  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getParentKey() {
    return parentKey;
  }

  public void setParentKey(String parentKey) {
    this.parentKey = parentKey;
  }

}
