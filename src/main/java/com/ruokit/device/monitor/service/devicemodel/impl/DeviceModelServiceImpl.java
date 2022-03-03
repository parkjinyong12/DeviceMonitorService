package com.ruokit.device.monitor.service.devicemodel.impl;

import com.ruokit.device.monitor.model.data.DeviceModel;
import com.ruokit.device.monitor.model.data.MfgCom;
import com.ruokit.device.monitor.model.view.devicemodel.DeviceModelDetail;
import com.ruokit.device.monitor.model.view.devicemodel.DeviceModelRegist;
import com.ruokit.device.monitor.repository.DeviceModelRepository;
import com.ruokit.device.monitor.repository.MfgComRepository;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import com.ruokit.device.monitor.service.devicemodel.DeviceModelService;
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
public class DeviceModelServiceImpl implements DeviceModelService {

    @Autowired
    private DeviceModelRepository dvcMdlRepository;

    @Autowired
    private MfgComRepository mfgComRepository;

    @Override
    public Page<DeviceModelDetail> getDeviceModelList(String cmpnId, Pageable pageable) throws Exception {

        // 로그인 사용자의 회사ID
        if(cmpnId==null) throw new Exception("manufacturerCompanyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        MfgCom userMfgCom = mfgComRepository.findByCmpnId(cmpnIdToLong);

        List<DeviceModelDetail> viewList = new ArrayList<DeviceModelDetail>();
        Page<DeviceModel> deviceModelPage = dvcMdlRepository.findByMfgComId(userMfgCom.getMfgComId(), pageable);

        for(DeviceModel deviceModel: deviceModelPage.getContent()) {
            DeviceModelDetail deviceModelDetail = new DeviceModelDetail();
            Long dvcMdlId = deviceModel.getDvcMdlId();
            deviceModelDetail.setDvcMdlId(String.valueOf(dvcMdlId));
            deviceModelDetail.setDvcMdlNm(deviceModel.getDvcMdlNm());

            MfgCom mfgCom = mfgComRepository.findByMfgComId(deviceModel.getMfgComId());
            deviceModelDetail.setMfgComNm(mfgCom.getMfgComNm());

            viewList.add(deviceModelDetail);
        }
        return new PageImpl<DeviceModelDetail>(viewList, pageable, deviceModelPage.getTotalElements());
    }

    @Override
    public List<DeviceModelDetail> getDeviceModelList(String mfgComId) throws Exception {
        List<DeviceModelDetail> viewList = new ArrayList<DeviceModelDetail>();
        if(mfgComId==null) throw new Exception("manufacturerCompanyId가 없으면 데이터를 조회할 수 없다.");
        Long mfgCoIdToLong = ConvertUtilService.castingToLong(mfgComId);
        List<DeviceModel> deviceModelList = dvcMdlRepository.findByMfgComId(mfgCoIdToLong);

        for(DeviceModel deviceModel: deviceModelList) {
            DeviceModelDetail deviceModelDetail = new DeviceModelDetail();
            Long dvcMdlId = deviceModel.getDvcMdlId();
            deviceModelDetail.setDvcMdlId(String.valueOf(dvcMdlId));
            deviceModelDetail.setDvcMdlNm(deviceModel.getDvcMdlNm());

            MfgCom mfgCom = mfgComRepository.findByMfgComId(deviceModel.getMfgComId());
            deviceModelDetail.setMfgComNm(mfgCom.getMfgComNm());

            viewList.add(deviceModelDetail);
        }
        return viewList;
    }

    @Override
    public void insertDeviceModel(DeviceModelRegist regist) throws Exception {

        String mfgComId = regist.getMfgComId();

        if(mfgComId==null) throw new Exception("manufacturerCompanyId가 없으면 데이터를 등록할 수 없다.");
        Long mfgCoIdToLong = ConvertUtilService.castingToLong(mfgComId);

        // 디바이스 모델 등록
        DeviceModel dvcMdl = new DeviceModel();
        dvcMdl.setDvcMdlNm(regist.getDvcMdlNm());
        dvcMdl.setMfgComId(mfgCoIdToLong);

        dvcMdlRepository.save(dvcMdl);
    }

    @Override
    public void updateDeviceModel(DeviceModelDetail update) throws Exception {
        DeviceModel dvcMdl = new DeviceModel();

        String dvcMdlId = update.getDvcMdlId();
        String mfgComId = update.getMfgComId();
        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 수정할 수 없다.");
        if(mfgComId==null) throw new Exception("manufacturerCompanyId가 없으면 데이터를 수정할 수 없다.");

        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
        Long mfgComIdToLong = ConvertUtilService.castingToLong(mfgComId);

        // 디바이스 모델 수정
        dvcMdl.setDvcMdlId(dvcMdlIdToLong);
        dvcMdl.setDvcMdlNm(update.getDvcMdlNm());
        dvcMdl.setMfgComId(mfgComIdToLong);
        dvcMdlRepository.save(dvcMdl);
    }

    @Override
    public void deleteDeviceModel(DeviceModelDetail delete) throws Exception {

        DeviceModel dvcMdl = new DeviceModel();
        String dvcMdlId = delete.getDvcMdlId();

        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 삭제할 수 없다.");

        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
        dvcMdl.setDvcMdlId(dvcMdlIdToLong);
        dvcMdlRepository.delete(dvcMdl);
    }

    @Override
    public DeviceModel getDeviceModelDetail(String dvcMdlId) throws Exception {
        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 조회할 수 없다.");
        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
        return dvcMdlRepository.findByDvcMdlId(dvcMdlIdToLong);
    }
}
