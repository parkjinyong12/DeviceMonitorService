package com.ruokit.device.monitor.model.service.user;

import java.util.List;

public class LoginUserSession {
    private String loginId;
    private String loginNm;
    private String cmpnId;
    private String cmpnNm;
    private List<String> roleList;

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

    public String getCmpnId() {
        return cmpnId;
    }

    public void setCmpnId(String cmpnId) {
        this.cmpnId = cmpnId;
    }

    public String getCmpnNm() {
        return cmpnNm;
    }

    public void setCmpnNm(String cmpnNm) {
        this.cmpnNm = cmpnNm;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
