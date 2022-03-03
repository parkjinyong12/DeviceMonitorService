package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.Hndset;
import com.ruokit.device.monitor.model.data.HndsetOpen;
import com.ruokit.device.monitor.model.data.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HndsetRepository extends JpaRepository<Hndset, Long> {
    Page<Hndset> findByCmpnId(Long cmpnId, Pageable pageable);
    Page<Hndset> findByCmpnIdAndHndsetNumContaining(Long cmpnId, String hndsetNum, Pageable pageable);
    Page<Hndset> findByCmpnIdAndDvcId(Long cmpnId, Long dvcId, Pageable pageable);
    Page<Hndset> findByCmpnIdAndDvcIdAndHndsetNumContaining(Long cmpnId, Long dvcId, String hndsetNum, Pageable pageable);
    Hndset findByHndsetId(Long hndsetId);
    Hndset findByDvcId(Long dvcId);
}
