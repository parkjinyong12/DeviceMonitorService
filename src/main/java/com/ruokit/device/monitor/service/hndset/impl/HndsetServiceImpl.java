package com.ruokit.device.monitor.service.hndset.impl;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.*;
import com.ruokit.device.monitor.model.view.hndset.HndsetDetail;
import com.ruokit.device.monitor.model.view.hndset.HndsetOpenDetail;
import com.ruokit.device.monitor.model.view.hndset.HndsetOpenRegist;
import com.ruokit.device.monitor.model.view.hndset.HndsetRegist;
import com.ruokit.device.monitor.repository.*;
import com.ruokit.device.monitor.service.common.util.ConvertUtilService;
import com.ruokit.device.monitor.service.hndset.HndsetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HndsetServiceImpl implements HndsetService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceModelRepository dvcMdlRepository;

    @Autowired
    private CmpnRepository cmpnRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private HndsetOpenRepository hndsetOpenRepository;

    @Autowired
    private HndsetRepository hndsetRepository;

    @Autowired
    private CdRepository cdRepository;

    private static Logger logger = LoggerFactory.getLogger(HndsetService.class);

    @Override
    public Page<HndsetDetail> getUsableHndsetList(String cmpnId, Pageable pageable) throws Exception {
        return getHndsetList(cmpnId, null, true, pageable);
    }

    @Override
    public Page<HndsetDetail> getUsableHndsetList(String cmpnId, String hndsetNum, Pageable pageable) throws Exception {
        return getHndsetList(cmpnId, hndsetNum, true, pageable);
    }

    @Override
    public Page<HndsetDetail> getHndsetList(String cmpnId, Pageable pageable) throws Exception {
        return getHndsetList(cmpnId, null, false, pageable);
    }

    @Override
    public Page<HndsetDetail> getHndsetList(String cmpnId, String hndsetNum, Pageable pageable) throws Exception {
        return getHndsetList(cmpnId, hndsetNum, false, pageable);
    }

    public Page<HndsetDetail> getHndsetList(String cmpnId, String hndsetNum, Boolean fgUsable, Pageable pageable) throws Exception {

        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 조회할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        List<HndsetDetail> hndsetDetailList = new ArrayList<HndsetDetail>();
        Page<Hndset> hndsetPage = null;
        if(fgUsable) {
            // 사용가능한 데이터유심만
            if(hndsetNum == null) {
                hndsetPage = hndsetRepository.findByCmpnIdAndDvcId(cmpnIdToLong, null, pageable);
            } else {
                hndsetPage = hndsetRepository.findByCmpnIdAndDvcIdAndHndsetNumContaining(cmpnIdToLong, null, hndsetNum, pageable);
            }
        } else {
            // 모든 데이터유심
            if(hndsetNum == null) {
                hndsetPage = hndsetRepository.findByCmpnId(cmpnIdToLong, pageable);
            } else {
                hndsetPage = hndsetRepository.findByCmpnIdAndHndsetNumContaining(cmpnIdToLong, hndsetNum, pageable);
            }
        }

        Company cmpn = cmpnRepository.findByCmpnId(cmpnIdToLong);

        for (Hndset hndset : hndsetPage.getContent()) {

            HndsetDetail hndsetDetail = new HndsetDetail();
            Long hndsetId = hndset.getHndsetId();

            // 유심 전화번호
            hndsetDetail.setHndsetId(String.valueOf(hndsetId));
            hndsetDetail.setHndsetNum(hndset.getHndsetNum());

            // 최근 개통상태 1건
            HndsetOpen hndsetOpen = hndsetOpenRepository.findFirstByHndsetIdOrderByHndsetOpenIdDesc(hndsetId);
            hndsetDetail.setHndsetOpenSt(hndsetOpen.getHndsetOpenSt());
            hndsetDetail.setHndsetOpenStNm(hndsetOpen.getHndsetOpenStNm());

            // 최근 개통상태 1건에 관련된 데이터제공사
            hndsetDetail.setHndsetCarrierCd(hndsetOpen.getHndsetCarrierCd());
            hndsetDetail.setHndsetCarrierNm(hndsetOpen.getHndsetCarrierNm());

            // 회사정보
            hndsetDetail.setCmpnId(String.valueOf(cmpn.getCmpnId()));
            hndsetDetail.setCmpnNm(cmpn.getCmpnNm());

            hndsetDetailList.add(hndsetDetail);
        }

        return new PageImpl<HndsetDetail>(hndsetDetailList, pageable, hndsetPage.getTotalElements());
    }

    @Override
    public Page<HndsetOpenDetail> getHndsetOpenList(String hndsetId, Pageable pageable) throws Exception {
        if(hndsetId==null) throw new Exception("handsetId가 없으면 데이터를 조회할 수 없다.");
        Long hndsetIdToLong = ConvertUtilService.castingToLong(hndsetId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<HndsetOpenDetail> hndsetOpenDetailList = new ArrayList<HndsetOpenDetail>();
        Page<HndsetOpen> hndsetOpenPage = hndsetOpenRepository.findByHndsetId(hndsetIdToLong, pageable);

        for (HndsetOpen hndsetOpen : hndsetOpenPage.getContent()) {

            HndsetOpenDetail hndsetOpenDetail = new HndsetOpenDetail();

            hndsetOpenDetail.setHndsetOpenId(hndsetOpen.getHndsetOpenId());
            hndsetOpenDetail.setHndsetId(hndsetOpen.getHndsetId());

            // 개통상태, 데이터 제공사
            hndsetOpenDetail.setHndsetOpenSt(hndsetOpen.getHndsetOpenSt());
            hndsetOpenDetail.setHndsetOpenStNm(hndsetOpen.getHndsetOpenStNm());
            hndsetOpenDetail.setHndsetCarrierCd(hndsetOpen.getHndsetCarrierCd());
            hndsetOpenDetail.setHndsetCarrierNm(hndsetOpen.getHndsetCarrierNm());

            // 처리일자
            hndsetOpenDetail.setRegDt(formatter.format(hndsetOpen.getRegDt()));

            hndsetOpenDetailList.add(hndsetOpenDetail);
        }
        return new PageImpl<HndsetOpenDetail>(hndsetOpenDetailList, pageable, hndsetOpenPage.getTotalElements());
    }

    @Override
    public void insertHndset(HndsetRegist regist) throws Exception {

        String cmpnId = regist.getCmpnId();
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 등록할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        Hndset hndset = new Hndset();
        hndset.setHndsetNum(regist.getHndsetNum());
        hndset.setCmpnId(cmpnIdToLong);
        Hndset result = hndsetRepository.save(hndset);
        Long hndsetId = result.getHndsetId();

        HndsetOpen hndsetOpen = new HndsetOpen();

        hndsetOpen.setHndsetId(hndsetId);
        // 데이터 제공사
        Code operatorCd = cdRepository.findByCd(regist.getHndsetCarrierCd());
        hndsetOpen.setHndsetCarrierCd(operatorCd.getCd());
        hndsetOpen.setHndsetCarrierNm(operatorCd.getCdDc());

        // 데이터 유심 개통상태
        Code openStCd = cdRepository.findByCd(CommonCode.HNDSET_OPEN_STATUS_UNOPENED);
        hndsetOpen.setHndsetOpenSt(openStCd.getCd());
        hndsetOpen.setHndsetOpenStNm(openStCd.getCdDc());
        hndsetOpenRepository.save(hndsetOpen);
    }

    @Override
    public void insertHndsetOpen(HndsetOpenRegist regist) throws Exception {

        String hndsetId = regist.getHndsetId();
        if(hndsetId==null) throw new Exception("handsetId가 없으면 데이터를 등록할 수 없다.");
        Long hndsetIdToLong = ConvertUtilService.castingToLong(hndsetId);

        HndsetOpen hndsetOpen = new HndsetOpen();
        hndsetOpen.setHndsetId(hndsetIdToLong);

        // 데이터유심 개통상태
        Code hndsetOpenSt = cdRepository.findByCd(regist.getHndsetOpenSt());
        hndsetOpen.setHndsetOpenSt(hndsetOpenSt.getCd());
        hndsetOpen.setHndsetOpenStNm(hndsetOpenSt.getCdDc());

        // 데이터 제공사
        Code hndsetCarrierCd = cdRepository.findByCd(regist.getHndsetCarrierCd());
        hndsetOpen.setHndsetCarrierCd(hndsetCarrierCd.getCd());
        hndsetOpen.setHndsetCarrierNm(hndsetCarrierCd.getCdDc());

        hndsetOpenRepository.save(hndsetOpen);
    }

    @Override
    public void updateHndset(HndsetDetail update) throws Exception {

        String hndsetId = update.getHndsetId();
        String cmpnId = update.getCmpnId();

        if(hndsetId==null) throw new Exception("handsetId 없으면 데이터를 수정할 수 없다.");
        Long hndsetIdToLong = ConvertUtilService.castingToLong(hndsetId);
        if(cmpnId==null) throw new Exception("companyId가 없으면 데이터를 수정할 수 없다.");
        Long cmpnIdToLong = ConvertUtilService.castingToLong(cmpnId);

        String hndsetNum = update.getHndsetNum();
        if(hndsetNum==null) throw new Exception("hndsetNumber가 없으면 데이터를 수정할 수 없다.");

//        HndsetOpen hndsetOpen = hndsetOpenRepository.findByHndsetOpenId(hndsetOpenIdToLong);
//        Code operatorCd = cdRepository.findByCd(update.getHndsetOperatorCd());
//        hndsetOpen.setHndsetOperatorCd(operatorCd.getCd());
//        hndsetOpen.setHndsetOperatorNm(operatorCd.getCdDc());
//        Code openingCd = cdRepository.findByCd(update.getHndsetOpenSt());
//        hndsetOpen.setHndsetOpenSt(openingCd.getCd());
//        hndsetOpen.setHndsetOpenStNm(openingCd.getCdDc());
//
//        Long hndsetId = hndsetOpen.getHndsetId();
//        Hndset hndset = hndsetRepository.findByHndsetId(hndsetId);
//        hndset.setHndsetNum(update.getHndsetNum());
    }

    @Override
    public HndsetDetail getHndsetDetail(String hndsetId) throws Exception {
        if(hndsetId==null) throw new Exception("handsetOpeningId가 없으면 데이터를 조회할 수 없다.");
        Long hndsetIdToLong = ConvertUtilService.castingToLong(hndsetId);

        HndsetDetail hndsetDetail = new HndsetDetail();

        Hndset hndset = hndsetRepository.findByHndsetId(hndsetIdToLong);
        hndsetDetail.setHndsetNum(hndset.getHndsetNum());
        hndsetDetail.setCmpnId(String.valueOf(hndset.getCmpnId()));
        HndsetOpen hndsetOpen = hndsetOpenRepository.findFirstByHndsetIdOrderByHndsetOpenIdDesc(hndsetIdToLong);
        hndsetDetail.setHndsetCarrierCd(hndsetOpen.getHndsetCarrierCd());
        hndsetDetail.setHndsetOpenSt(hndsetOpen.getHndsetOpenSt());

        return hndsetDetail;
    }

    @Override
    public void deleteHndset(String hndsetId) throws Exception {

        if(hndsetId==null) throw new Exception("handsetId 없으면 데이터를 삭제할 수 없다.");
        Long hndsetIdToLong = ConvertUtilService.castingToLong(hndsetId);

        Hndset hndset = hndsetRepository.findByHndsetId(hndsetIdToLong);
        List<HndsetOpen> hndsetList = hndsetOpenRepository.findByHndsetId(hndsetIdToLong);

        // 개통이력 삭제
        hndsetOpenRepository.deleteAll(hndsetList);
        // 데이터 유심정보 삭제
        hndsetRepository.delete(hndset);
    }
}