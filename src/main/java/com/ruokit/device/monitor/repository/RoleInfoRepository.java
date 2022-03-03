package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleInfoRepository extends JpaRepository<RoleInfo, Long> {
    RoleInfo findByRoleInfoId(Long roleInfoId);
}
