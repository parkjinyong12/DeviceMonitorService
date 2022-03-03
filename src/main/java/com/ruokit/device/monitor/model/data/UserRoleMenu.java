package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "user_role_menu")
public class UserRoleMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleMenuId;
    private String roleCd;
    private Long menuId;

    public Long getUserRoleMenuId() {
        return userRoleMenuId;
    }

    public void setUserRoleMenuId(Long userRoleMenuId) {
        this.userRoleMenuId = userRoleMenuId;
    }

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
