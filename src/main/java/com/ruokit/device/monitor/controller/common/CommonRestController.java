package com.ruokit.device.monitor.controller.common;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.Code;
import com.ruokit.device.monitor.model.data.Company;
import com.ruokit.device.monitor.model.data.MfgCom;
import com.ruokit.device.monitor.model.data.PageView;
import com.ruokit.device.monitor.model.view.cmpn.CmpnDetail;
import com.ruokit.device.monitor.model.view.mfgcom.MfgComDetail;
import com.ruokit.device.monitor.model.view.vehicle.VehicleDetail;
import com.ruokit.device.monitor.repository.CdRepository;
import com.ruokit.device.monitor.service.cmpn.CmpnService;
import com.ruokit.device.monitor.service.mfgcom.MfgComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data/common")
public class CommonRestController {

    @Autowired
    private CmpnService cmpnService;

    @Autowired
    private MfgComService mfgComService;

    @Autowired
    private CdRepository cdRepository;

    @PostMapping("/mfg-list")
    public List<MfgComDetail> getMfgList() {
        // 리스트 조회
        List<MfgCom> mfgComList = mfgComService.getMfgList();

        List<MfgComDetail> mfgComDetailList = new ArrayList<MfgComDetail>();
        for(MfgCom mfgCom: mfgComList) {

            MfgComDetail mfgComDetail = new MfgComDetail();
            mfgComDetail.setMfgComId(mfgCom.getMfgComId());
            mfgComDetail.setMfgComNm(mfgCom.getMfgComNm());

            mfgComDetailList.add(mfgComDetail);
        }
        return mfgComDetailList;
    }

    @PostMapping("/cmpn-list")
    public List<CmpnDetail> getCmpnList() {
        // 리스트 조회
        List<Company> cmpnList = cmpnService.getCmpnList();
        List<CmpnDetail> cmpnDetailList = new ArrayList<CmpnDetail>();
        for(Company cmpn : cmpnList) {
            CmpnDetail cmpnDetail = new CmpnDetail();
            cmpnDetail.setCmpnId(String.valueOf(cmpn.getCmpnId()));
            cmpnDetail.setCmpnNm(cmpn.getCmpnNm());
            cmpnDetailList.add(cmpnDetail);
        }
        return cmpnDetailList;
    }

    @GetMapping("/cmpn/{cmpnId}")
    public CmpnDetail getCmpnList(@PathVariable(value = "cmpnId") String cmpnId) throws Exception {
        // 리스트 조회
        Company cmpn = cmpnService.getCmpnList(cmpnId);
        CmpnDetail cmpnDetail = new CmpnDetail();
        cmpnDetail.setCmpnId(String.valueOf(cmpn.getCmpnId()));
        cmpnDetail.setCmpnNm(cmpn.getCmpnNm());
        return cmpnDetail;
    }
}
