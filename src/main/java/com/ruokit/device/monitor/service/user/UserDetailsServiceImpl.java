package com.ruokit.device.monitor.service.user;

import com.ruokit.device.monitor.mapper.userinfo.MapperUserInfo;
import com.ruokit.device.monitor.model.data.RoleInfo;
import com.ruokit.device.monitor.model.data.UserInfo;
import com.ruokit.device.monitor.model.data.UserRole;
import com.ruokit.device.monitor.model.service.user.LoginUserModel;
import com.ruokit.device.monitor.model.service.user.UserInfoModel;
import com.ruokit.device.monitor.model.service.user.UserModel;
import com.ruokit.device.monitor.repository.RoleInfoRepository;
import com.ruokit.device.monitor.repository.UserInfoRepository;
import com.ruokit.device.monitor.repository.UserRoleRepository;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleInfoRepository roleInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername() in UserDetailsServiceImpl");
        UserInfoModel userInfo = findUserbyUername(username);

        boolean accountNonLocked = true;
        boolean accountNonExpired = true;
        boolean enabled = true;

        String loginId = null;
        String encodingPassword = null;

        if (userInfo != null) {
            loginId = userInfo.getLoginId();

            if ("Y".equals(userInfo.getAcntYn())) {
                enabled = false;
                logger.info("??? ?????? ???????????????. ????????? ????????? : " + loginId);
            } else if ("Y".equals(userInfo.getAcntLockYn())) {
                accountNonLocked = false;
                logger.info("????????????(???????????? ???????????? ?????? ???)??? ?????? ?????? ???????????????. ????????? ????????? : " + loginId);
            } else if ("Y".equals(userInfo.getAcntExpYn())) {
                accountNonExpired = false;
                logger.info("??????????????? ????????? ???????????????. ????????? ????????? : " + loginId);
            }

            // ????????? ??????????????? ??????????????????. ??????, DB??? ???????????? ??????????????? ??????????????? ??????.
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            encodingPassword = encoder.encode(userInfo.getPasswd());

        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        LoginUserModel loginUserModel = new LoginUserModel(loginId, encodingPassword, enabled, accountNonExpired, true,
                accountNonLocked, userInfo.getUserRoles());

        return loginUserModel;
    }

    private UserInfoModel findUserbyUername(String username) {

        // ????????? ??????
        UserInfo userInfo = userInfoRepository.findByLoginId(username);
        UserInfoModel userInfoModel = MapperUserInfo.mapUserInfo(userInfo);

        // ????????? ?????? ??????
        if(userInfoModel != null) {
            List<UserRole> userRoleList = userRoleRepository.findByLoginId(username);
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            userRoleList.forEach(userRole -> {
                authorities.add(new SimpleGrantedAuthority(String.valueOf(userRole.getRoleCd())));
            });
            userInfoModel.setUserRoles(authorities);
        }
        return userInfoModel;
    }
}
