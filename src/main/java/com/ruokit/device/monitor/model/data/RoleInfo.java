package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "role_info")
public class RoleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleInfoId;

    private String roleCd;
    private String roleNm;

    public Long getRoleInfoId() {
        return roleInfoId;
    }

    public void setRoleInfoId(Long roleInfoId) {
        this.roleInfoId = roleInfoId;
    }

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }

    public String getRoleNm() {
        return roleNm;
    }

    public void setRoleNm(String roleNm) {
        this.roleNm = roleNm;
    }
}
