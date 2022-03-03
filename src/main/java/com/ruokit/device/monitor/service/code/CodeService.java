package com.ruokit.device.monitor.service.code;

import com.ruokit.device.monitor.model.data.Code;

import java.util.List;

public interface CodeService {
    public List<Code> getCodeList(String upperCdNm) throws Exception;
}
