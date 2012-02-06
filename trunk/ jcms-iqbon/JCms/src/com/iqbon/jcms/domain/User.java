package com.iqbon.jcms.domain;

public class User extends CMSDomain{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String userName;
  
  private String nickName;
  
  private int positionNum;
  
  private String email;
  
  private String telephone;
  
  private String mobile;
  
  private String addUser;
  
  private String addTime;
  
  private String lastLogin;
  
  private String positionName;
  
  private String password;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public int getPositionNum() {
    return positionNum;
  }

  public void setPositionNum(int positionNum) {
    this.positionNum = positionNum;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getAddUser() {
    return addUser;
  }

  public void setAddUser(String addUser) {
    this.addUser = addUser;
  }

  public String getAddTime() {
    return addTime;
  }

  public void setAddTime(String addTime) {
    this.addTime = addTime;
  }

  public String getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(String lastLogin) {
    this.lastLogin = lastLogin;
  }

  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
}
