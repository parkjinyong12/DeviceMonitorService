package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.DeviceModel;
import com.ruokit.device.monitor.model.data.MfgCom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MfgComRepository extends JpaRepository<MfgCom, Long> {
    MfgCom findByMfgComId(Long mfgComId);
    MfgCom findByCmpnId(Long cmpnId);

}
