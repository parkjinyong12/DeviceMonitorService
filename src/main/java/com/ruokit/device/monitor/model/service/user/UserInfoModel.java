package com.ruokit.device.monitor.model.service.user;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

public class UserInfoModel {

  private Long userInfoId;
  private String regDt;
  private String regId;
  private String uptDt;
  private String uptId;
  private String acntExpYn;
  private String acntLockYn;
  private String acntYn;
  private String expireDt;
  private String failCnt;
  private String passwd;
  private String pwExpYn;
  private String loginId;
  private String loginNm;
  private Long cmpnId;

  private List<GrantedAuthority> userRoles;

  public Long getUserInfoId() {
    return userInfoId;
  }

  public void setUserInfoId(Long userInfoId) {
    this.userInfoId = userInfoId;
  }

  public String getRegDt() {
    return regDt;
  }

  public void setRegDt(String regDt) {
    this.regDt = regDt;
  }

  public String getRegId() {
    return regId;
  }

  public void setRegId(String regId) {
    this.regId = regId;
  }

  public String getUptDt() {
    return uptDt;
  }

  public void setUptDt(String uptDt) {
    this.uptDt = uptDt;
  }

  public String getUptId() {
    return uptId;
  }

  public void setUptId(String uptId) {
    this.uptId = uptId;
  }

  public String getAcntExpYn() {
    return acntExpYn;
  }

  public void setAcntExpYn(String acntExpYn) {
    this.acntExpYn = acntExpYn;
  }

  public String getAcntLockYn() {
    return acntLockYn;
  }

  public void setAcntLockYn(String acntLockYn) {
    this.acntLockYn = acntLockYn;
  }

  public String getAcntYn() {
    return acntYn;
  }

  public void setAcntYn(String acntYn) {
    this.acntYn = acntYn;
  }

  public String getExpireDt() {
    return expireDt;
  }

  public void setExpireDt(String expireDt) {
    this.expireDt = expireDt;
  }

  public String getFailCnt() {
    return failCnt;
  }

  public void setFailCnt(String failCnt) {
    this.failCnt = failCnt;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getPwExpYn() {
    return pwExpYn;
  }

  public void setPwExpYn(String pwExpYn) {
    this.pwExpYn = pwExpYn;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getLoginNm() {
    return loginNm;
  }

  public void setLoginNm(String loginNm) {
    this.loginNm = loginNm;
  }

  public List<GrantedAuthority> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<GrantedAuthority> userRoles) {
    this.userRoles = userRoles;
  }

  public Long getCmpnId() {
    return cmpnId;
  }

  public void setCmpnId(Long cmpnId) {
    this.cmpnId = cmpnId;
  }
}
