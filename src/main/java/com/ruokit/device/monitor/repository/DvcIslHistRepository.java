package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.Device;
import com.ruokit.device.monitor.model.data.DvcIslHist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DvcIslHistRepository extends JpaRepository<DvcIslHist, Long> {
    List<DvcIslHist> findByDvcId(Long dvcId);
    Page<DvcIslHist> findByDvcId(Long dvcId, Pageable pageable);
    Page<DvcIslHist> findByDvcIdAndDvcIslStIn(Long dvcId, List<String> dvcIslStList, Pageable pageable);
    // dvcId로 조회한 최근 1건
    DvcIslHist findFirstByDvcIdOrderByDvcIslHistIdDesc(Long dvcId);
    Long countByDvcId(Long dvcId);
}
