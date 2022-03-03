package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.DeviceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceModelRepository extends JpaRepository<DeviceModel, Long> {
    DeviceModel findByDvcMdlId(Long dvcMdlId);
    List<DeviceModel> findByMfgComId(Long mfgComId);
    Page<DeviceModel> findByMfgComId(Long mfgComId, Pageable pageable);
}
