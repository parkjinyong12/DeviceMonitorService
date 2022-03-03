package com.ruokit.device.monitor.service.install.impl;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.controller.install.dvcIslController;
import com.ruokit.device.monitor.model.data.*;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.device.DeviceRegist;
import com.ruokit.device.monitor.model.view.install.DvcIslDetail;
import com.ruokit.device.monitor.model.view.install.DvcIslRegist;
import com.ruokit.device.monitor.repository.*;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import com.ruokit.device.monitor.service.install.DvcIslService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DvcIslServiceImpl implements DvcIslService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CmpnRepository cmpnRepository;

    @Autowired
    private CdRepository cdRepository;

    @Autowired
    private HndsetRepository hndsetRepository;

    @Autowired
    private DvcIslHistRepository dvcIslHistRepository;

    @Autowired
    private DeviceModelRepository dvcMdlRepository;

    private final static Logger logger = LoggerFactory.getLogger(DvcIslService.class);

    @Override
    public Page<DeviceDetail> getDeviceList(String cmpnId, Pageable pageable) throws Exception {

        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<DeviceDetail> deviceDetailList = new ArrayList<DeviceDetail>();

        List<String> dvcIslStList = new ArrayList<String>();
        // 등록, 설치요청
        dvcIslStList.add(CommonCode.DEVICE_INSTALL_STATUS_REGIST);
        dvcIslStList.add(CommonCode.DEVICE_INSTALL_STATUS_REQUEST);

        Page<Device> devicePage = deviceRepository.findByCmpnIdAndLstDvcIslStIn(cmpnIdToLong, dvcIslStList, pageable);

        for (Device device : devicePage.getContent()) {

            DeviceDetail deviceDetail = new DeviceDetail();
            deviceDetail.setDvcId(String.valueOf(device.getDvcId()));
            Company cmpn = cmpnRepository.findByCmpnId(device.getCmpnId());
            if(cmpn != null) {
                deviceDetail.setCmpnId(String.valueOf(cmpn.getCmpnId()));
                deviceDetail.setCmpnNm(cmpn.getCmpnNm());
            }
            DeviceModel dvcMdl = dvcMdlRepository.findByDvcMdlId(device.getDvcMdlId());
            deviceDetail.setDvcMdlNm(dvcMdl.getDvcMdlNm());

            Hndset hndset = hndsetRepository.findByDvcId(device.getDvcId());
            if(hndset != null) {
                deviceDetail.setHndsetId(String.valueOf(hndset.getHndsetId()));
                deviceDetail.setHndsetNum(hndset.getHndsetNum());
            }

            Vehicle vehicle = vehicleRepository.findByDvcId(device.getDvcId());
            if(vehicle != null) {
                deviceDetail.setVehicleId(String.valueOf(vehicle.getVehicleId()));
                deviceDetail.setVehicleNum(vehicle.getVehicleNum());
            }

            DvcIslHist dvcIslHist = dvcIslHistRepository.findFirstByDvcIdOrderByDvcIslHistIdDesc(device.getDvcId());
            deviceDetail.setDvcIslStNm(dvcIslHist.getDvcIslStNm());
            deviceDetail.setDvcIslSt(dvcIslHist.getDvcIslSt());
            deviceDetail.setDvcIslStRegDt(formatter.format(dvcIslHist.getRegDt()));
            deviceDetailList.add(deviceDetail);
        }
        return new PageImpl<DeviceDetail>(deviceDetailList, pageable, devicePage.getTotalElements());
    }

    @Override
    public Page<DvcIslDetail> getDvcIslList(String dvcId, Pageable pageable) throws Exception {

        if(dvcId==null) throw new Exception("deviceId가 없으면 데이터를 조회할 수 없다.");
        Long dvcIdToLong = ConvertUtilService.castingToLong(dvcId);

        List<DvcIslDetail> dvcIslDetailList = new ArrayList<DvcIslDetail>();
        Page<DvcIslHist> dvcIslPage = dvcIslHistRepository.findByDvcId(dvcIdToLong, pageable);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Set<String> statusSet = new HashSet<String>();
        // 설치요청, 설치요청승인, 설치완료
        statusSet.add(CommonCode.DEVICE_INSTALL_STATUS_REGIST);
        statusSet.add(CommonCode.DEVICE_INSTALL_STATUS_REQUEST);

        for (DvcIslHist dvcIslHist : dvcIslPage.getContent()) {
            // 사전에 지정된 설치상태 이력만 리스트에 담는다.
            if(statusSet.contains(dvcIslHist.getDvcIslSt())) {
                DvcIslDetail dvcIslDetail = new DvcIslDetail();
                dvcIslDetail.setDvcIslHistId(String.valueOf(dvcIslHist.getDvcIslHistId()));
                dvcIslDetail.setDvcIslSt(dvcIslHist.getDvcIslSt());
                dvcIslDetail.setDvcIslStNm(dvcIslHist.getDvcIslStNm());
                dvcIslDetail.setRegDt(formatter.format(dvcIslHist.getRegDt()));
                dvcIslDetailList.add(dvcIslDetail);
            }
        }
        // 이동수단 식별번호와 디바이스 전화번호는 모든이력에서 동일한 정보를 사용한다.
        Device dvc = deviceRepository.findByDvcId(dvcIdToLong);

        // 유심 전화번호
        Hndset hndset = hndsetRepository.findByDvcId(dvc.getDvcId());
        // 이동수단
        Vehicle vehicle = vehicleRepository.findByDvcId(dvc.getDvcId());

        for(DvcIslDetail dvcIslHist : dvcIslDetailList) {
            if(vehicle != null) {
                dvcIslHist.setVehicleNum(vehicle.getVehicleNum());
            }
            if(hndset != null) {
                dvcIslHist.setHndsetId(String.valueOf(hndset.getHndsetId()));
                dvcIslHist.setHndsetNum(hndset.getHndsetNum());
            }
        }
        return new PageImpl<DvcIslDetail>(dvcIslDetailList, pageable, dvcIslPage.getTotalElements());
    }

    @Override
    public void insertDvcIsl(DvcIslRegist regist) throws Exception {

        String dvcId = regist.getDvcId();
        if(dvcId==null) throw new Exception("deviceId가 없으면 데이터를 등록할 수 없다.");
        Long dvcIdToLong = ConvertUtilService.castingToLong(dvcId);

        // 디바이스 설치상태
        DvcIslHist dvcIslHist = new DvcIslHist();
        Code cd = cdRepository.findByCd(regist.getDvcIslSt());
        if(cd != null) {
            dvcIslHist.setDvcIslSt(cd.getCd());
            dvcIslHist.setDvcIslStNm(cd.getCdDc());

            // device에서도 최근 설치진행이력을 저장한다.
            Device device = deviceRepository.findByDvcId(dvcIdToLong);
            device.setLstDvcIslSt(cd.getCd());
        } else {
            dvcIslHist.setDvcIslSt(regist.getDvcIslSt());
            dvcIslHist.setDvcIslStNm("등록되지 않은 코드");
        }
        dvcIslHist.setRegUserId(regist.getRegUserId());
        dvcIslHist.setDvcId(dvcIdToLong);
        dvcIslHistRepository.save(dvcIslHist);
    }

    @Override
    public void deleteDevice(String dvcId) throws Exception {

        Device device = new Device();
        if(dvcId==null) throw new Exception("deviceId가 없으면 데이터를 삭제할 수 없다.");
        Long dvcIdToLong = ConvertUtilService.castingToLong(dvcId);

        device.setDvcId(dvcIdToLong);
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

        // 이동수단
        Vehicle vehicle = vehicleRepository.findByDvcId(device.getDvcId());
        if(vehicle != null) {
            result.setVehicleId(String.valueOf(vehicle.getVehicleId()));
            result.setVehicleNum(vehicle.getVehicleNum());
        }
        return result;
    }
}
