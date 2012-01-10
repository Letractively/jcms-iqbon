package com.iqbon.jcms.domain;

public class Role extends CMSDomain {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String roleNum;
  
  private String roleName;
  
  private String description;

  public String getRoleNum() {
    return roleNum;
  }

  public void setRoleNum(String roleNum) {
    this.roleNum = roleNum;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
}
