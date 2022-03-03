package com.ruokit.device.monitor.service.device;

import com.ruokit.device.monitor.model.data.Device;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.device.DeviceRegist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceService {
    public Page<DeviceDetail> getDeviceList(String cmpnId, Pageable pageable) throws Exception;
    public Device insertDevice(DeviceRegist regist) throws Exception;
    public void updateDevice(DeviceDetail update) throws Exception;
    public void deleteDevice(String dvcId) throws Exception;
    public DeviceDetail getDeviceDetail(String dvcId) throws Exception;
}
