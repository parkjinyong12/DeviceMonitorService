package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByLoginId(String loginId);
}
