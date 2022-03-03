package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.HndsetOpen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HndsetOpenRepository extends JpaRepository<HndsetOpen, Long> {
    List<HndsetOpen> findByHndsetId(Long hndsetId);
    Page<HndsetOpen> findByHndsetId(Long hndsetId, Pageable pageable);
    // hndsetId로 조회한 최근 1건
    HndsetOpen findFirstByHndsetIdOrderByHndsetOpenIdDesc(Long hndsetId);
}
