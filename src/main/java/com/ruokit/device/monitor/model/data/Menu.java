package com.ruokit.device.monitor.model.data;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String menuCd;
    private String menuNm;
    private String menuFeather;
    private String menuSrvVwNm;
    private String menuPath;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuCd() {
        return menuCd;
    }

    public void setMenuCd(String menuCd) {
        this.menuCd = menuCd;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public String getMenuFeather() {
        return menuFeather;
    }

    public void setMenuFeather(String menuFeather) {
        this.menuFeather = menuFeather;
    }

    public String getMenuSrvVwNm() {
        return menuSrvVwNm;
    }

    public void setMenuSrvVwNm(String menuSrvVwNm) {
        this.menuSrvVwNm = menuSrvVwNm;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }
}
