package com.ruokit.device.monitor.service.install;

import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.device.DeviceRegist;
import com.ruokit.device.monitor.model.view.install.DvcIslDetail;
import com.ruokit.device.monitor.model.view.install.DvcIslRegist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DvcIslService {
    public Page<DeviceDetail> getDeviceList(String cmpnId, Pageable pageable) throws Exception;
    public Page<DvcIslDetail> getDvcIslList(String dvcId, Pageable pageable) throws Exception;
    public void insertDvcIsl(DvcIslRegist dvcIslRegist) throws Exception;
    public void deleteDevice(String dvcId) throws Exception;
    public DeviceDetail getDeviceDetail(String dvcId) throws Exception;
}
