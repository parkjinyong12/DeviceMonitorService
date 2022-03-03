package com.ruokit.device.monitor.service.cmpn;

import com.ruokit.device.monitor.model.data.Company;
import com.ruokit.device.monitor.model.data.MfgCom;

import java.util.List;

public interface CmpnService {
    public List<Company> getCmpnList();
    public Company getCmpnList(String cmpnId) throws Exception;
}
