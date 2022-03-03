package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.Company;
import com.ruokit.device.monitor.model.data.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByDvcId(Long dvcId);
    List<Device> findByCmpnId(Long cmpnId);
    Page<Device> findByCmpnId(Long cmpnId, Pageable Pageable);
    Page<Device> findByCmpnIdAndLstDvcIslStIn(Long cmpnId, List<String> dvcIslStList, Pageable Pageable);
    Long countByCmpnId(Long cmpnId);
    Page<Device> countByCmpnId(Long cmpnId, PageRequest pageRequest);
}
