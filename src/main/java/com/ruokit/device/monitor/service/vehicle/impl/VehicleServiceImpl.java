package com.ruokit.device.monitor.service.vehicle.impl;

import com.ruokit.device.monitor.model.data.*;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.device.DeviceRegist;
import com.ruokit.device.monitor.model.view.vehicle.VehicleDetail;
import com.ruokit.device.monitor.model.view.vehicle.VehicleRegist;
import com.ruokit.device.monitor.repository.*;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import com.ruokit.device.monitor.service.device.DeviceService;
import com.ruokit.device.monitor.service.vehicle.VehicleService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceModelRepository dvcMdlRepository;

    @Autowired
    private CmpnRepository cmpnRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CdRepository cdRepository;

    @Override
    public Page<VehicleDetail> getVehicleList(String cmpnId, Pageable pageable) throws Exception {
        return getVehicleList(cmpnId, null, false, pageable);
    }

    @Override
    public Page<VehicleDetail> getVehicleList(String cmpnId, String vehicleNum, Pageable pageable) throws Exception {
        return getVehicleList(cmpnId, vehicleNum, false, pageable);
    }

    @Override
    public Page<VehicleDetail> getUsableVehicleList(String cmpnId, Pageable pageable) throws Exception {
        return getVehicleList(cmpnId, null, true, pageable);
    }

    @Override
    public Page<VehicleDetail> getUsableVehicleList(String cmpnId, String vehicleNum, Pageable pageable) throws Exception {
        return getVehicleList(cmpnId, vehicleNum, true, pageable);
    }

    public Page<VehicleDetail> getVehicleList(String cmpnId, String vehicleNum, Boolean fgUsable, Pageable pageable) throws Exception {
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        List<VehicleDetail> vehicleDetailList = new ArrayList<VehicleDetail>();
        Page<Vehicle> vehiclePage = null;

        if(fgUsable) {
            // 사용가능한 차량만
            if(vehicleNum == null) {
                vehiclePage = vehicleRepository.findByCmpnIdAndDvcId(cmpnIdToLong, null, pageable);
            } else {
                vehiclePage = vehicleRepository.findByCmpnIdAndDvcIdAndVehicleNumContaining(cmpnIdToLong, null, vehicleNum, pageable);
            }
        } else {
            // 모든 차량
            if(vehicleNum == null) {
                vehiclePage = vehicleRepository.findByCmpnId(cmpnIdToLong, pageable);
            } else {
                vehiclePage = vehicleRepository.findByCmpnIdAndVehicleNumContaining(cmpnIdToLong, vehicleNum, pageable);
            }
        }

        for (Vehicle vehicle : vehiclePage.getContent()) {
            VehicleDetail vehicleDetail = new VehicleDetail();
            vehicleDetail.setVehicleId(String.valueOf(vehicle.getVehicleId()));
            Code cd = cdRepository.findByCd(vehicle.getVehicleTp());
            vehicleDetail.setVehicleTp(String.valueOf(cd.getCdId()));
            vehicleDetail.setVehicleTpNm(cd.getCdDc());
            vehicleDetail.setVehicleNum(vehicle.getVehicleNum());
            Company cmpn = cmpnRepository.findByCmpnId(vehicle.getCmpnId());
            vehicleDetail.setCmpnId(String.valueOf(cmpn.getCmpnId()));
            vehicleDetail.setCmpnNm(cmpn.getCmpnNm());
            vehicleDetail.setDvcId(String.valueOf(vehicle.getDvcId()));
            vehicleDetailList.add(vehicleDetail);
        }
        return new PageImpl<VehicleDetail>(vehicleDetailList, pageable, vehiclePage.getTotalElements());
    }

    @Override
    public void insertVehicle(VehicleRegist regist) throws Exception {

        String cmpnId = regist.getCmpnId();
        String vehicleTp = regist.getVehicleTp();
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 등록할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        Vehicle vehicle = new Vehicle();
        vehicle.setCmpnId(cmpnIdToLong);
        vehicle.setVehicleTp(regist.getVehicleTp());
        vehicle.setVehicleNum(regist.getVehicleNum());
        vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehicle(VehicleDetail update) throws Exception {
        Vehicle vehicle = new Vehicle();

        String vehicleId = update.getVehicleId();
        String vehicleTp = update.getVehicleTp();
        String cmpnId = update.getCmpnId();

        if(vehicleId==null) throw new Exception("vehicleId가 없으면 데이터를 수정할 수 없다.");
        Long vehicleIdToLong = ConvertUtilService.castingToLong(vehicleId);
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 수정할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        vehicle.setVehicleId(vehicleIdToLong);
        vehicle.setVehicleTp(update.getVehicleTp());
        vehicle.setVehicleNum(update.getVehicleNum());
        vehicle.setCmpnId(cmpnIdToLong);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicle(String vehicleId) throws Exception {

        Vehicle vehicle = new Vehicle();
        if(vehicleId==null) throw new Exception("vehicleId가 없으면 데이터를 삭제할 수 없다.");
        Long vehicleIdToLong = ConvertUtilService.castingToLong(vehicleId);

        vehicle.setVehicleId(vehicleIdToLong);
        vehicleRepository.delete(vehicle);
    }

    @Override
    public VehicleDetail getVehicleDetail(String vehicleId) throws Exception {
        if(vehicleId==null) throw new Exception("vehicleId가 없으면 데이터를 조회할 수 없다.");
        Long vehicleIdToLong = ConvertUtilService.castingToLong(vehicleId);

        Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleIdToLong);
        VehicleDetail result  = new VehicleDetail();
        result.setVehicleId(String.valueOf(vehicle.getVehicleId()));
        result.setVehicleNum(vehicle.getVehicleNum());
        // 차량구분
        Code cd = cdRepository.findByCd(vehicle.getVehicleTp());
        result.setVehicleTp(String.valueOf(cd.getCdId()));
        result.setVehicleTpNm(cd.getCdNm());
        // 회사
        Company cmpn = cmpnRepository.findByCmpnId(vehicle.getCmpnId());
        result.setCmpnId(String.valueOf(cmpn.getCmpnId()));
        result.setCmpnNm(cmpn.getCmpnNm());

        // 장착 디바이스
        result.setDvcId(String.valueOf(vehicle.getDvcId()));
        return result;
    }
}