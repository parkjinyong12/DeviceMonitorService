package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.Device;
import com.ruokit.device.monitor.model.data.DvcStk;
import com.ruokit.device.monitor.model.data.DvcStkGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DvcStkRepository extends JpaRepository<DvcStk, Long> {
    @Query(value = "select dvc_mdl_id as dvcMdlId ,count(1) as dvcMdlCnt from dvc_stk where cmpn_id = ?1 group by dvc_mdl_id",
            countQuery = "select count(distinct(dvc_mdl_id)) from dvc_stk where cmpn_id = ?1",
            nativeQuery = true)
    Page<DvcStkGroup> findByCmpnId(Long cmpnId, Pageable pageable);
    Page<DvcStk> findByCmpnIdAndDvcMdlId(Long cmpnId, Long dvcMdlId, Pageable pageable);
    DvcStk findByDvcStkId(Long dvcStkId);
    Boolean existsByDvcMdlIdAndDvcSn(Long dvcMdlId, String dvcSn);
}
