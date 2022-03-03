package com.ruokit.device.monitor.service.vehicle;

import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.device.DeviceRegist;
import com.ruokit.device.monitor.model.view.vehicle.VehicleDetail;
import com.ruokit.device.monitor.model.view.vehicle.VehicleRegist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {
    public Page<VehicleDetail> getVehicleList(String cmpnId, Pageable pageable) throws Exception;
    public Page<VehicleDetail> getVehicleList(String cmpnId, String vehicleNum, Pageable pageable) throws Exception;
    public Page<VehicleDetail> getUsableVehicleList(String cmpnId, Pageable pageable) throws Exception;
    public Page<VehicleDetail> getUsableVehicleList(String cmpnId, String vehicleNum, Pageable pageable) throws Exception;
    public void insertVehicle(VehicleRegist regist) throws Exception;
    public void updateVehicle(VehicleDetail update) throws Exception;
    public void deleteVehicle(String vehicleId) throws Exception;
    public VehicleDetail getVehicleDetail(String vehicleId) throws Exception;
}
