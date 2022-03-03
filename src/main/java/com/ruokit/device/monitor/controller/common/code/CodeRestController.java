package com.ruokit.device.monitor.controller.common.code;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.Code;
import com.ruokit.device.monitor.model.view.code.CodeDetail;
import com.ruokit.device.monitor.service.code.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/data/code")
public class CodeRestController {

    @Autowired
    private CodeService codeService;

    @GetMapping("/vehicle-tp-list")
    public List<CodeDetail> getVehicleTypeList() throws Exception {
        return getCodeList(CommonCode.VEHICLE_TYPE);
    }

    @GetMapping("/hndset-open-st-list")
    public List<CodeDetail> getHndsetOpenStList() throws Exception {
        return getCodeList(CommonCode.HNDSET_OPEN_STATUS);
    }

    @GetMapping("/hndset-carrier-list")
    public List<CodeDetail> getHndsetCarrierList() throws Exception {
        return getCodeList(CommonCode.HNDSET_CARRIER);
    }

    @GetMapping("/dvc-install-st-list")
    public List<CodeDetail> getDvcInstallStList() throws Exception {
        return getCodeList(CommonCode.DEVICE_INSTALL_STATUS);
    }

    @GetMapping("/hndset-mnt-st-list")
    public List<CodeDetail> getHndsetMntStList() throws Exception {
        return getCodeList(CommonCode.HNDSET_MOUNT_STATUS);
    }

    private List<CodeDetail> getCodeList(String codeName) throws Exception {
        List<CodeDetail> codeDetailList = new ArrayList<CodeDetail>();
        List<Code> cdList = codeService.getCodeList(codeName);
        cdList.forEach(code -> {
            CodeDetail codeDetail = new CodeDetail();
            codeDetail.setCdId(String.valueOf(code.getCdId()));
            codeDetail.setCd(code.getCd());
            codeDetail.setCdNm(code.getCdNm());
            codeDetail.setCdDc(code.getCdDc());
            codeDetailList.add(codeDetail);
        });
        return codeDetailList;
    }
}
