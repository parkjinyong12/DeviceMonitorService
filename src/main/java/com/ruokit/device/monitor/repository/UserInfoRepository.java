package com.ruokit.device.monitor.repository;


import com.ruokit.device.monitor.model.data.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByLoginId(String loginId);
}
