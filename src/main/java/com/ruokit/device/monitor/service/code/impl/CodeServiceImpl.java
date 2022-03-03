package com.ruokit.device.monitor.service.code.impl;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.Code;
import com.ruokit.device.monitor.repository.CdRepository;
import com.ruokit.device.monitor.service.code.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CdRepository cdRepository;

    public List<Code> getCodeList(String upperCd) throws Exception {
        if(upperCd==null) throw new Exception("upper Code가 없으면 데이터를 조회할 수 없다.");
        return cdRepository.findByUpperCd(upperCd);
    }
}
