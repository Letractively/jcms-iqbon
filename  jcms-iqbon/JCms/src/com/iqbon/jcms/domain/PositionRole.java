package com.iqbon.jcms.domain;

public class PositionRole extends CMSDomain{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int positionNum;
  
  private int roleName;
  
  private String description;

  public int getPositionNum() {
    return positionNum;
  }

  public void setPositionNum(int positionNum) {
    this.positionNum = positionNum;
  }

  public int getRoleName() {
    return roleName;
  }

  public void setRoleName(int roleName) {
    this.roleName = roleName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
