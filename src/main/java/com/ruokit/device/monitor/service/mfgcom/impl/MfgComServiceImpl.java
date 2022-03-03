package com.ruokit.device.monitor.service.mfgcom.impl;

import com.ruokit.device.monitor.model.data.MfgCom;
import com.ruokit.device.monitor.repository.MfgComRepository;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import com.ruokit.device.monitor.service.mfgcom.MfgComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MfgComServiceImpl implements MfgComService {

    @Autowired
    private MfgComRepository mfgComRepository;

    @Override
    public List<MfgCom> getMfgList() {
        return mfgComRepository.findAll();
    }

    @Override
    public MfgCom getMfgCom(String cmpnId) throws Exception {

        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        return mfgComRepository.findByCmpnId(cmpnIdToLong);
    }
}
