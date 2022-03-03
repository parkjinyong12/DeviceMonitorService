package com.ruokit.device.monitor.service.common.session.impl;

import com.ruokit.device.monitor.model.data.*;
import com.ruokit.device.monitor.model.service.user.LoginUserModel;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.repository.*;
import com.ruokit.device.monitor.service.common.session.CommonSessionService;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.CollationElementIterator;
import java.util.*;

@Service
@Transactional
public class CommonSessionServiceImpl implements CommonSessionService {

    @Autowired
    private CmpnRepository cmpnRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private RoleInfoRepository roleInfoRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleMenuRepository userRoleMenuRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void setUserInfo(HttpServletRequest req) {

        HttpSession httpSession = req.getSession();

        LoginUserModel loginInfo = (LoginUserModel) httpSession.getAttribute("loginInfo");
        String loginId = loginInfo.getUsername();

        UserInfo userInfo = userInfoRepository.findByLoginId(loginId);
        Company cmpn = cmpnRepository.findByCmpnId(userInfo.getCmpnId());

        List<String> authList = new ArrayList<String>();
        loginInfo.getAuthorities().forEach( grantedAuthority -> {
            authList.add(grantedAuthority.getAuthority());
        });

        LoginUserSession loginUser = new LoginUserSession();
        loginUser.setLoginId(userInfo.getLoginId());
        loginUser.setLoginNm(userInfo.getLoginNm());
        loginUser.setCmpnId(String.valueOf(cmpn.getCmpnId()));
        loginUser.setCmpnNm(cmpn.getCmpnNm());
        loginUser.setRoleList(authList);

        httpSession.setAttribute("loginUser", loginUser);
    }

    @Override
    public void setMenuList(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        LoginUserSession loingUser = (LoginUserSession) httpSession.getAttribute("loginUser");

        Set<Menu> menuSet = new LinkedHashSet<Menu>();
        List<String> roleList = loingUser.getRoleList();

        if(roleList != null) {
            // user role 리스트
            roleList.forEach(roleCd -> {
                List<UserRoleMenu> userRoleMenuList = userRoleMenuRepository.findByRoleCd(roleCd);
                if(userRoleMenuList != null) {

                    // role에 해당하는 menu set
                    userRoleMenuList.forEach(menu -> {
                        menuSet.add(menuRepository.findByMenuId(menu.getMenuId()));
                    });
                }
            });
        }
        httpSession.setAttribute("menuList", new ArrayList<Menu>(menuSet));
    }

    @Override
    public LoginUserSession getLoginUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (LoginUserSession) session.getAttribute("loginUser");
    }
}
