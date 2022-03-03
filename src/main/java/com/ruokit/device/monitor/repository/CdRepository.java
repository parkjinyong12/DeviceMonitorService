package com.ruokit.device.monitor.repository;

import com.ruokit.device.monitor.model.data.Code;
import com.ruokit.device.monitor.model.data.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdRepository extends JpaRepository<Code, Long> {
    Code findByCdNm(String cdNm);
    Code findByCd(String code);
    List<Code> findByUpperCd(String upperCd);
}
