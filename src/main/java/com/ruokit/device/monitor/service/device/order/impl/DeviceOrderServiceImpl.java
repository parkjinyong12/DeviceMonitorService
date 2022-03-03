package com.ruokit.device.monitor.service.device.order.impl;

import com.ruokit.device.monitor.model.data.DeviceModel;
import com.ruokit.device.monitor.model.data.DvcStk;
import com.ruokit.device.monitor.model.data.DvcStkGroup;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkDetail;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkGroupDetail;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkRegist;
import com.ruokit.device.monitor.repository.DeviceModelRepository;
import com.ruokit.device.monitor.repository.DvcStkRepository;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import com.ruokit.device.monitor.service.device.order.DeviceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeviceOrderServiceImpl implements DeviceOrderService {

    @Autowired
    DvcStkRepository dvcStkRepository;

    @Autowired
    DeviceModelRepository dvcMdlRepository;

    @Override
    public Page<DvcStkGroupDetail> getDeviceStockGroupList(String cmpnId, Pageable pageable) throws Exception {
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<DvcStkGroupDetail> dvcStkGroupDetailList = new ArrayList<DvcStkGroupDetail>();
        Page<DvcStkGroup> dvcStkGroupPage = dvcStkRepository.findByCmpnId(cmpnIdToLong, pageable);

        for (DvcStkGroup dvcStkGroup : dvcStkGroupPage.getContent()) {
            DvcStkGroupDetail dvcStkGroupDetail = new DvcStkGroupDetail();
            // 디바이스 모델
            DeviceModel dvcMdl = dvcMdlRepository.findByDvcMdlId(dvcStkGroup.getDvcMdlId());
            dvcStkGroupDetail.setDvcMdlId(String.valueOf(dvcMdl.getDvcMdlId()));
            dvcStkGroupDetail.setDvcMdlNm(dvcMdl.getDvcMdlNm());
            dvcStkGroupDetail.setDvcMdlCnt(String.valueOf(dvcStkGroup.getDvcMdlCnt()));
            dvcStkGroupDetailList.add(dvcStkGroupDetail);
        }
        return new PageImpl<DvcStkGroupDetail>(dvcStkGroupDetailList, pageable, dvcStkGroupPage.getTotalElements());
    }

    public Page<DvcStkDetail> getDeviceStockList(String cmpnId, String dvcMdlId, Pageable pageable) throws Exception {
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);
        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 조회할 수 없다.");
        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<DvcStkDetail> dvcStkDetailList = new ArrayList<DvcStkDetail>();
        Page<DvcStk> dvcStkPage = dvcStkRepository.findByCmpnIdAndDvcMdlId(cmpnIdToLong, dvcMdlIdToLong, pageable);

        for (DvcStk dvcStk : dvcStkPage.getContent()) {
            DvcStkDetail dvcStkDetail = new DvcStkDetail();

            dvcStkDetail.setDvcStkId(String.valueOf(dvcStk.getDvcStkId()));
            // 디바이스 모델
            DeviceModel dvcMdl = dvcMdlRepository.findByDvcMdlId(dvcStk.getDvcMdlId());
            dvcStkDetail.setDvcMdlId(String.valueOf(dvcMdl.getDvcMdlId()));
            dvcStkDetail.setDvcMdlNm(dvcMdl.getDvcMdlNm());
            dvcStkDetail.setDvcSn(dvcStk.getDvcSn());
            dvcStkDetail.setRegDt(formatter.format(dvcStk.getRegDt()));
            dvcStkDetailList.add(dvcStkDetail);
        }
        return new PageImpl<DvcStkDetail>(dvcStkDetailList, pageable, dvcStkPage.getTotalElements());
    }

    @Override
    public DvcStk insertDeviceStock(DvcStkRegist regist) throws Exception {

        String dvcMdlId = regist.getDvcMdlId();
        String cmpnId = regist.getCmpnId();
        
        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 등록할 수 없다.");
        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 등록할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        DvcStk dvcStk = new DvcStk();
        dvcStk.setCmpnId(cmpnIdToLong);

        dvcStk.setDvcMdlId(dvcMdlIdToLong);
        dvcStk.setDvcSn(regist.getDvcSn());

        return dvcStkRepository.save(dvcStk);
    }

    @Override
    public List<DvcStk> insertDeviceStocks(List<DvcStkRegist> regists) throws Exception {

        List<DvcStk> dvcStkList = new ArrayList<DvcStk>();
        for (DvcStkRegist regist: regists) {

            String dvcMdlId = regist.getDvcMdlId();
            String cmpnId = regist.getCmpnId();

            if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 등록할 수 없다.");
            Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
            if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 등록할 수 없다.");
            Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

            DvcStk dvcStk = new DvcStk();
            dvcStk.setCmpnId(cmpnIdToLong);

            dvcStk.setDvcMdlId(dvcMdlIdToLong);
            dvcStk.setDvcSn(regist.getDvcSn());

            dvcStkList.add(dvcStk);
        }
        return dvcStkRepository.saveAll(dvcStkList);
    }

    @Override
    public DeviceModel getDeviceModelStock(String dvcMdlId) throws Exception {
        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 조회할 수 없다.");
        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);
        return dvcMdlRepository.findByDvcMdlId(dvcMdlIdToLong);
    }

    @Override
    public void updateDeviceStock(DvcStkDetail update) throws Exception {
        String dvcStkId = update.getDvcStkId();
        if(dvcStkId==null) throw new Exception("deviceStockId가 없으면 데이터를 조회할 수 없다.");
        Long dvcStkIdToLong = ConvertUtilService.castingToLong(dvcStkId);
        DvcStk dvcStk = dvcStkRepository.findByDvcStkId(dvcStkIdToLong);
        String dvcSn = update.getDvcSn();
        if(dvcSn != null && dvcSn.length() > 0) {
            dvcStk.setDvcSn(dvcSn);
        }
        dvcStk.setRegDt(LocalDateTime.now());
    }

    @Override
    public Boolean isExistDeviceStocks(DvcStkDetail dvcStkDetail) throws Exception {
        String dvcMdlId = dvcStkDetail.getDvcMdlId();
        if(dvcMdlId==null) throw new Exception("deviceModelId가 없으면 데이터를 조회할 수 없다.");
        Long dvcMdlIdToLong = ConvertUtilService.castingToLong(dvcMdlId);

        return dvcStkRepository.existsByDvcMdlIdAndDvcSn(dvcMdlIdToLong, dvcStkDetail.getDvcSn());
    }
}
