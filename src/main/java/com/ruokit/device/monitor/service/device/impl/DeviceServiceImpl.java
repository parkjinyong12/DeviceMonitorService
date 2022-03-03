package com.ruokit.device.monitor.service.device.impl;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.*;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.device.DeviceRegist;
import com.ruokit.device.monitor.model.view.install.DvcIslRegist;
import com.ruokit.device.monitor.repository.*;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import com.ruokit.device.monitor.service.device.DeviceService;
import com.ruokit.device.monitor.service.install.DvcIslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceModelRepository dvcMdlRepository;

    @Autowired
    private CmpnRepository cmpnRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private MfgComRepository mfgComRepository;

    @Autowired
    private CdRepository cdRepository;

    @Autowired
    private DvcIslHistRepository dvcIslHistRepository;

    @Autowired
    private HndsetRepository hndsetRepository;

    @Override
    public Page<DeviceDetail> getDeviceList(String cmpnId, Pageable pageable) throws Exception {
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<DeviceDetail> deviceDetailList = new ArrayList<DeviceDetail>();
        Page<Device> devicePage = deviceRepository.findByCmpnId(cmpnIdToLong, pageable);

        Set<String> statusSet = new HashSet<String>();
        // 설치요청, 설치요청승인, 설치완료
        statusSet.add(CommonCode.DEVICE_INSTALL_STATUS_REGIST);
        statusSet.add(CommonCode.DEVICE_INSTALL_STATUS_REQUEST);

        for (Device device : devicePage.getContent()) {
            DeviceDetail deviceDetail = new DeviceDetail();
            deviceDetail.setDvcId(String.valueOf(device.getDvcId()));

            // 유심 전화번호
            Hndset hndset = hndsetRepository.findByDvcId(device.getDvcId());
            if(hndset != null) {
                deviceDetail.setHndsetId(String.valueOf(hndset.getHndsetId()));
                deviceDetail.setHndsetNum(hndset.getHndsetNum());
            }

            // 회사정보
            Company cmpn = cmpnRepository.findByCmpnId(device.getCmpnId());
            if(cmpn != null) {
                deviceDetail.setCmpnId(String.valueOf(cmpn.getCmpnId()));
                deviceDetail.setCmpnNm(cmpn.getCmpnNm());
            }

            // 디바이스 모델
            DeviceModel dvcMdl = dvcMdlRepository.findByDvcMdlId(device.getDvcMdlId());
            deviceDetail.setDvcMdlNm(dvcMdl.getDvcMdlNm());

            // 이동수단
            Vehicle vehicle = vehicleRepository.findByDvcId(device.getDvcId());
            if(vehicle != null) {
                deviceDetail.setVehicleId(String.valueOf(vehicle.getVehicleId()));
                deviceDetail.setVehicleNum(vehicle.getVehicleNum());
            }

            // 디바이스 설치
            DvcIslHist dvcIslHist = dvcIslHistRepository.findFirstByDvcIdOrderByDvcIslHistIdDesc(device.getDvcId());
            deviceDetail.setDvcIslStNm(dvcIslHist.getDvcIslStNm());
            deviceDetail.setDvcIslStRegDt(formatter.format(dvcIslHist.getRegDt()));

            deviceDetailList.add(deviceDetail);
        }
        return new PageImpl<DeviceDetail>(deviceDetailList, pageable, devicePage.getTotalElements());
    }

    @Override
    public Device insertDevice(DeviceRegist regist) throws Exception {

        String dvcMdlId = regist.getDvcMdlId();
        String cmpnId = regist.getCmpnId();
        String vehicleId = regist.getVehicleId();
        String hndsetId = regist.getHndsetId();

        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 등록할 수 없다.");
        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 등록할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        Device device = new Device();
        device.setCmpnId(cmpnIdToLong);

        device.setDvcMdlId(dvcMdlIdToLong);
        device.setHndsetMntSt(regist.getHndsetMntSt());

        Device result = deviceRepository.save(device);

        // hndsetId가 없어도 디바이스를 등록할 수 있다.
        if(hndsetId != null) {
            Long hndsetIdToLong = ConvertUtilService.castingToLong(hndsetId);
            Hndset hndset = hndsetRepository.findByHndsetId(hndsetIdToLong);
            hndset.setDvcId(result.getDvcId());
        }

        // vehicleId가 없이도 디바이스를 등록할 수 있다.
        if(vehicleId!=null) {
            Long vehicleIdToLong = ConvertUtilService.castingToLong(vehicleId);
            Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleIdToLong);
            vehicle.setDvcId(result.getDvcId());
        }
        return result;
    }

    @Override
    public void updateDevice(DeviceDetail update) throws Exception {
//        Device device = new Device();

        String dvcId = update.getDvcId();
        String dvcMdlId = update.getDvcMdlId();
        String cmpnId = update.getCmpnId();
        String vehicleId = update.getVehicleId();
        String hndsetId = update.getHndsetId();

        if(dvcId==null) throw new Exception("deviceId가 없으면 데이터를 수정할 수 없다.");
        Long dvcIdToLong = ConvertUtilService.castingToLong(dvcId);
        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 수정할 수 없다.");
        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 수정할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);
        if(vehicleId==null) throw new Exception("vehicleId가 없으면 데이터를 수정할 수 없다.");
        Long vehicleIdToLong = ConvertUtilService.castingToLong(vehicleId);
        if(hndsetId==null) throw new Exception("handsetId 없으면 데이터를 수정할 수 없다.");
        Long hndsetIdToLong = ConvertUtilService.castingToLong(hndsetId);

        Device device = deviceRepository.findByDvcId(dvcIdToLong);

        device.setHndsetMntSt(update.getHndsetMntSt());
        device.setDvcId(dvcIdToLong);
        device.setCmpnId(cmpnIdToLong);
        device.setDvcMdlId(dvcMdlIdToLong);

        // 이전 데이터유심에 디바이스 정보를 지운다.
        Hndset preHndset = hndsetRepository.findByDvcId(device.getDvcId());
        if(preHndset != null) {
            preHndset.setDvcId(null);
        }

        // 데이터유심에 디바이스 정보를 등록한다.
        Hndset hndset = hndsetRepository.findByHndsetId(hndsetIdToLong);
        hndset.setDvcId(device.getDvcId());

        // 이전 이동수단에 디바이스 정보를 지운다.
        Vehicle preVehicle = vehicleRepository.findByDvcId(device.getDvcId());
        if(preVehicle != null) {
            preVehicle.setDvcId(null);
        }

        // 이동수단에 디바이스 정보를 등록한다.
        Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleIdToLong);
        vehicle.setDvcId(device.getDvcId());

        deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(String dvcId) throws Exception {

        if(dvcId==null) throw new Exception("deviceId가 없으면 데이터를 삭제할 수 없다.");
        Long dvcIdToLong = ConvertUtilService.castingToLong(dvcId);

        // 이동수단에 디바이스 정보 삭제
        Vehicle vehicle = vehicleRepository.findByDvcId(dvcIdToLong);
        if(vehicle != null) {
            vehicle.setDvcId(null);
        }
        // 데이터유심에 디바이스 정보 삭제
        Hndset hndset = hndsetRepository.findByDvcId(dvcIdToLong);
        if(hndset != null) {
            hndset.setDvcId(null);
        }

        Device device = new Device();
        device.setDvcId(dvcIdToLong);

        // 디바이스 삭제
        deviceRepository.delete(device);
    }

    @Override
    public DeviceDetail getDeviceDetail(String dvcId) throws Exception {
        if(dvcId==null) throw new Exception("deviceId가 없으면 데이터를 조회할 수 없다.");
        Long dvcIdToLong = ConvertUtilService.castingToLong(dvcId);

        Device device = deviceRepository.findByDvcId(dvcIdToLong);
        DeviceDetail result  = new DeviceDetail();
        result.setDvcId(String.valueOf(device.getDvcId()));
        result.setCmpnId(String.valueOf(device.getCmpnId()));
        result.setDvcMdlId(String.valueOf(device.getDvcMdlId()));
        // 모델명으로 제조회사ID 조회
        DeviceModel dvcMdl = dvcMdlRepository.findByDvcMdlId(device.getDvcMdlId());
        result.setMfgComId(String.valueOf(dvcMdl.getMfgComId()));

        // 유심 전화번호
        Hndset hndset = hndsetRepository.findByDvcId(device.getDvcId());
        if(hndset != null) {
            result.setHndsetId(String.valueOf(hndset.getHndsetId()));
            result.setHndsetNum(hndset.getHndsetNum());
        }

        // 유심 장착상태
        result.setHndsetMntSt(device.getHndsetMntSt());

        Vehicle vehicle = vehicleRepository.findByDvcId(device.getDvcId());
        if(vehicle != null) {
            result.setVehicleId(String.valueOf(vehicle.getVehicleId()));
            result.setVehicleNum(vehicle.getVehicleNum());
        }
        DvcIslHist dvcIslHist = dvcIslHistRepository.findFirstByDvcIdOrderByDvcIslHistIdDesc(device.getDvcId());
        result.setDvcIslSt(dvcIslHist.getDvcIslSt());
        return result;
    }
}
