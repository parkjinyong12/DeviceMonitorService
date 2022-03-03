package com.ruokit.device.monitor.service.common.session;

import com.ruokit.device.monitor.model.service.user.LoginUserSession;

import javax.servlet.http.HttpServletRequest;

public interface CommonSessionService {
    public void setUserInfo(HttpServletRequest req);
    public void setMenuList(HttpServletRequest req);
    public LoginUserSession getLoginUser(HttpServletRequest req);
}
