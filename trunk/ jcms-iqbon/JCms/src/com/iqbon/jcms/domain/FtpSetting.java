package com.iqbon.jcms.domain;

public class FtpSetting {
  
  public enum refreshRate {
    /**
     * 每分钟
     */
    minute,
    /**
     * 每小时
     */
    hour,

    /**
     *每天 
     */
    day
  };

  private String ftpIp;

  private String ftpPort;

  private String username;

  private String password;

  private String ftpPath;

  private int ftpPushRate;

  private boolean enable;

  public String getFtpIp() {
    return ftpIp;
  }

  public void setFtpIp(String ftpIp) {
    this.ftpIp = ftpIp;
  }

  public String getFtpPort() {
    return ftpPort;
  }

  public void setFtpPort(String ftpPort) {
    this.ftpPort = ftpPort;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFtpPath() {
    return ftpPath;
  }

  public void setFtpPath(String ftpPath) {
    this.ftpPath = ftpPath;
  }

  public int getFtpPushRate() {
    return ftpPushRate;
  }

  public void setFtpPushRate(int ftpPushRate) {
    this.ftpPushRate = ftpPushRate;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

}
