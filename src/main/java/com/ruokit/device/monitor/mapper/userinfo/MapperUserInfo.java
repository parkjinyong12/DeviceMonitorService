package com.ruokit.device.monitor.mapper.userinfo;

import com.ruokit.device.monitor.model.data.UserInfo;
import com.ruokit.device.monitor.model.service.user.UserInfoModel;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class MapperUserInfo {
    public static UserInfoModel mapUserInfo(UserInfo userInfo) {
        if(userInfo == null) return null;
        UserInfoModel model = new UserInfoModel();
        model.setUserInfoId(userInfo.getUserInfoId());
        model.setRegDt(userInfo.getRegDt());
        model.setRegId(userInfo.getRegId());
        model.setUptDt(userInfo.getUptDt());
        model.setUptId(userInfo.getUptId());
        model.setAcntExpYn(userInfo.getAcntExpYn());
        model.setAcntLockYn(userInfo.getAcntLockYn());
        model.setAcntYn(userInfo.getAcntYn());
        model.setExpireDt(userInfo.getExpireDt());
        model.setFailCnt(userInfo.getFailCnt());
        model.setPasswd(userInfo.getPasswd());
        model.setPwExpYn(userInfo.getPwExpYn());
        model.setLoginId(userInfo.getLoginId());
        model.setLoginNm(userInfo.getLoginNm());
        model.setCmpnId(userInfo.getCmpnId());
        return model;
    }
}
