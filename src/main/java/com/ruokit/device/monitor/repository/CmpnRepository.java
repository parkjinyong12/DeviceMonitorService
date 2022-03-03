package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.Company;
import com.ruokit.device.monitor.model.data.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmpnRepository extends JpaRepository<Company, Long> {
    Company findByCmpnId(Long cmpnId);
}
