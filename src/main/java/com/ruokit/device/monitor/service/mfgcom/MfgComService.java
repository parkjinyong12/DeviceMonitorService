package com.ruokit.device.monitor.service.mfgcom;

import com.ruokit.device.monitor.model.data.MfgCom;

import java.util.List;

public interface MfgComService {
    public List<MfgCom> getMfgList();
    public MfgCom getMfgCom(String cmpnId) throws Exception;
}
