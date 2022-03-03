package com.ruokit.device.monitor.service.cmpn.impl;

import com.ruokit.device.monitor.model.data.Company;
import com.ruokit.device.monitor.repository.CmpnRepository;
import com.ruokit.device.monitor.service.cmpn.CmpnService;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CmpnServiceImpl implements CmpnService {

    @Autowired
    private CmpnRepository cmpnRepository;

    @Override
    public List<Company> getCmpnList() {
        return cmpnRepository.findAll();
    }

    @Override
    public Company getCmpnList(String cmpnId) throws Exception {
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);
        return cmpnRepository.findByCmpnId(cmpnIdToLong);
    }
}
