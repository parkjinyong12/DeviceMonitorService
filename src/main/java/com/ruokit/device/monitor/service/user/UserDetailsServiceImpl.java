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
                logger.info("미 사용 계정입니다. 사용자 아이디 : " + loginId);
            } else if ("Y".equals(userInfo.getAcntLockYn())) {
                accountNonLocked = false;
                logger.info("특정사유(비밀번호 입력회수 초과 등)로 인해 잠긴 계정입니다. 사용자 아이디 : " + loginId);
            } else if ("Y".equals(userInfo.getAcntExpYn())) {
                accountNonExpired = false;
                logger.info("사용기한이 만료된 계정입니다. 사용자 아이디 : " + loginId);
            }

            // 임시로 패스워드를 인코딩합니다. 추후, DB에 암호화된 비밀번호를 저장하도록 수정.
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

        // 사용자 조회
        UserInfo userInfo = userInfoRepository.findByLoginId(username);
        UserInfoModel userInfoModel = MapperUserInfo.mapUserInfo(userInfo);

        // 사용자 권한 조회
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
