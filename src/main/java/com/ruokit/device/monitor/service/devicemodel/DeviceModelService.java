package com.ruokit.device.monitor.service.devicemodel;

import com.ruokit.device.monitor.model.data.DeviceModel;
import com.ruokit.device.monitor.model.view.devicemodel.DeviceModelDetail;
import com.ruokit.device.monitor.model.view.devicemodel.DeviceModelRegist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeviceModelService {
    public Page<DeviceModelDetail> getDeviceModelList(String cmpnId, Pageable pageable) throws Exception;
    public List<DeviceModelDetail> getDeviceModelList(String mfgComId) throws Exception;
    public void insertDeviceModel(DeviceModelRegist regist) throws Exception;
    public void updateDeviceModel(DeviceModelDetail update) throws Exception;
    public void deleteDeviceModel(DeviceModelDetail delete) throws Exception;
    public DeviceModel getDeviceModelDetail(String deviceModelId) throws Exception;
}
