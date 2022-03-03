package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuId(Long menuId);
}
