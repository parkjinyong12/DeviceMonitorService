package com.ruokit.device.monitor.service.user.impl;

import com.ruokit.device.monitor.mapper.userinfo.MapperUserInfo;
import com.ruokit.device.monitor.model.data.UserInfo;
import com.ruokit.device.monitor.model.service.user.UserInfoModel;
import com.ruokit.device.monitor.repository.UserInfoRepository;
import com.ruokit.device.monitor.repository.UserRoleRepository;
import com.ruokit.device.monitor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserInfoModel gerUserInfoModel(String loginId) {
        UserInfo userInfo = userInfoRepository.findByLoginId(loginId);
        return MapperUserInfo.mapUserInfo(userInfo);
    }
}
