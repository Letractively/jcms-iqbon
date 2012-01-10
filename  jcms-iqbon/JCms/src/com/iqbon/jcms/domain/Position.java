package com.iqbon.jcms.domain;

public class Position extends CMSDomain {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private int positionNum ;
  
  private String positionName;
  
  private String descript;

  public int getPositionNum() {
    return positionNum;
  }

  public void setPositionNum(int positionNum) {
    this.positionNum = positionNum;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public String getDescript() {
    return descript;
  }

  public void setDescript(String descript) {
    this.descript = descript;
  }
  
}
