package com.ruokit.device.monitor.model.service.user;

import javax.persistence.*;

public class UserRoleModel {

  private String userRoleId;
  private String roleCd;
  private String loginId;

  public String getUserRoleId() {
    return userRoleId;
  }

  public void setUserRoleId(String userRoleId) {
    this.userRoleId = userRoleId;
  }

  public String getRoleCd() {
    return roleCd;
  }

  public void setRoleCd(String roleCd) {
    this.roleCd = roleCd;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }
}
