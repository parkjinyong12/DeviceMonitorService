package com.ruokit.device.monitor.service.installconfirm.impl;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.*;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.install.DvcIslDetail;
import com.ruokit.device.monitor.model.view.install.DvcIslRegist;
import com.ruokit.device.monitor.repository.*;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import com.ruokit.device.monitor.service.installconfirm.DvcIslConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class DvcIslConfirmServiceImpl implements DvcIslConfirmService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CdRepository cdRepository;

    @Autowired
    private CmpnRepository cmpnRepository;

    @Autowired
    private DvcIslHistRepository dvcIslHistRepository;

    @Autowired
    private DeviceModelRepository dvcMdlRepository;

    @Autowired
    private HndsetRepository hndsetRepository;

    @Override
    public Page<DeviceDetail> getDeviceList(String cmpnId, Pageable pageable) throws Exception {
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        List<DeviceDetail> deviceDetailList = new ArrayList<DeviceDetail>();
        List<String> dvcIslStList = new ArrayList<String>();

        // 설치요청, 설치요청승인, 설치완료
        dvcIslStList.add(CommonCode.DEVICE_INSTALL_STATUS_REQUEST);
        dvcIslStList.add(CommonCode.DEVICE_INSTALL_STATUS_REQUEST_ACCEPT);
        dvcIslStList.add(CommonCode.DEVICE_INSTALL_STATUS_COMPLETE);

        Page<Device> devicePage = deviceRepository.findByCmpnIdAndLstDvcIslStIn(cmpnIdToLong, dvcIslStList, pageable);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
            // 이동수단이 등록된 경우
            if(vehicle != null) {
                deviceDetail.setVehicleId(String.valueOf(vehicle.getVehicleId()));
                deviceDetail.setVehicleNum(vehicle.getVehicleNum());
            }
            DvcIslHist dvcIslHist = dvcIslHistRepository.findFirstByDvcIdOrderByDvcIslHistIdDesc(device.getDvcId());
            deviceDetail.setDvcIslStNm(dvcIslHist.getDvcIslStNm());
            deviceDetail.setDvcIslStRegDt(formatter.format(dvcIslHist.getRegDt()));

            deviceDetailList.add(deviceDetail);
        }
        return new PageImpl<DeviceDetail>(deviceDetailList, pageable, devicePage.getTotalElements());
    }

    @Override
    public Page<DvcIslDetail> getDvcIslList(String dvcId, Pageable pageable) throws Exception {
        if(dvcId==null) throw new Exception("deviceId가 없으면 데이터를 조회할 수 없다.");
        Long dvcIdToLong = ConvertUtilService.castingToLong(dvcId);

        List<String> dvcIslStList = new ArrayList<String>();
        // 등록, 설치요청
        dvcIslStList.add(CommonCode.DEVICE_INSTALL_STATUS_REQUEST);
        dvcIslStList.add(CommonCode.DEVICE_INSTALL_STATUS_REQUEST_ACCEPT);
        dvcIslStList.add(CommonCode.DEVICE_INSTALL_STATUS_COMPLETE);

        List<DvcIslDetail> dvcIslDetailList = new ArrayList<DvcIslDetail>();
        Page<DvcIslHist> dvcIslHistPage = dvcIslHistRepository.findByDvcIdAndDvcIslStIn(dvcIdToLong, dvcIslStList, pageable);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (DvcIslHist dvcIslHist : dvcIslHistPage.getContent()) {
            DvcIslDetail dvcIslDetail = new DvcIslDetail();
            dvcIslDetail.setDvcIslHistId(String.valueOf(dvcIslHist.getDvcIslHistId()));
            dvcIslDetail.setDvcIslSt(dvcIslHist.getDvcIslSt());
            dvcIslDetail.setDvcIslStNm(dvcIslHist.getDvcIslStNm());
            dvcIslDetail.setRegDt(formatter.format(dvcIslHist.getRegDt()));
            dvcIslDetailList.add(dvcIslDetail);
        }
        // 이동수단 식별번호와 디바이스 전화번호는 모든이력에서 동일한 정보를 사용한다.
        Device dvc = deviceRepository.findByDvcId(dvcIdToLong);
        Hndset hndset = hndsetRepository.findByDvcId(dvc.getDvcId());

        Vehicle vehicle = vehicleRepository.findByDvcId(dvc.getDvcId());
        String vehicleNum = vehicle.getVehicleNum();

        for(DvcIslDetail dvcIslHist : dvcIslDetailList) {
            if(vehicle != null) {
                dvcIslHist.setVehicleNum(vehicleNum);
            }
            if(hndset != null) {
                dvcIslHist.setHndsetId(String. valueOf(hndset.getHndsetId()));
                dvcIslHist.setHndsetNum(hndset.getHndsetNum());
            }
        }
        return new PageImpl<DvcIslDetail>(dvcIslDetailList, pageable, dvcIslHistPage.getTotalElements());
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
}
