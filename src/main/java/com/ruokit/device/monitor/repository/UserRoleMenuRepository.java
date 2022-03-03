package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.UserRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMenuRepository extends JpaRepository<UserRoleMenu, Long> {
    List<UserRoleMenu> findByRoleCd(String roleCd);
}
