package com.ruokit.device.monitor.controller.install;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.PageView;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.install.DvcIslDetail;
import com.ruokit.device.monitor.model.view.install.DvcIslRegist;
import com.ruokit.device.monitor.common.service.PagingService;
import com.ruokit.device.monitor.service.device.DeviceService;
import com.ruokit.device.monitor.service.install.DvcIslService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/data/install")
public class dvcIslRestController {

    @Autowired
    private DvcIslService dvcIslService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PagingService pagingService;

    private static Logger logger = LoggerFactory.getLogger(dvcIslRestController.class);

    @GetMapping("/page")
    public PageView getPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");

        String cmpnId = loginUser.getCmpnId();

        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<DeviceDetail> deviceDetailPage = dvcIslService.getDeviceList(cmpnId, pageRequest);

        return pagingService.getPaging(deviceDetailPage);
    }

    @GetMapping("/detail")
    public PageView getDetailPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        String dvcId = request.getParameter("dvcId");

        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<DvcIslDetail> deviceDetailPage = dvcIslService.getDvcIslList(dvcId, pageRequest);

        return pagingService.getPaging(deviceDetailPage);
    }

    @PostMapping("/request")
    public String request(Model model, HttpServletRequest request, @RequestBody DvcIslRegist dvcIslRegist) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String loginId = loginUser.getLoginId();

        // 디바이스 설치 히스토리 저장
        dvcIslRegist.setDvcId(dvcIslRegist.getDvcId());
        String dvcIslSt = dvcIslRegist.getDvcIslSt();

        // 처리여부
        boolean fgProcess = true;
        String result = CommonCode.VIEW_PROCESSING_ENDED_ABNORMALLY;

        try {
            int dvcIslStToInt = Integer.valueOf(dvcIslSt);
            int stRequestToInt = Integer.valueOf(CommonCode.DEVICE_INSTALL_STATUS_REQUEST);

            // 들어온 값이 설치요청(1000203) 보다 크거나 같으면 처리하지 않는다.
            if(dvcIslStToInt >= stRequestToInt) {
                fgProcess = false;
            }
        } catch (NumberFormatException e) {
            logger.info("String -> Long 형변환 오류. value: " + dvcIslSt);
            return result;
        }

        if(fgProcess) {
            dvcIslRegist.setDvcIslSt(CommonCode.DEVICE_INSTALL_STATUS_REQUEST);
            dvcIslRegist.setRegUserId(loginId);
            dvcIslService.insertDvcIsl(dvcIslRegist);
            result = CommonCode.VIEW_PROCESSED_NORMALLY;
        } else {
            result = CommonCode.VIEW_ALREADY_BEEN_REACHED;
        }
        return result;
    }

    @PostMapping("/update")
    public String update(Model model, HttpServletRequest request, @RequestBody DeviceDetail deviceUpdate) throws Exception {

        deviceService.updateDevice(deviceUpdate);
        return "success";
    }

    @PostMapping("/delete")
    public String delete(Model model, HttpServletRequest request, @RequestBody DeviceDetail deviceDelete) throws Exception {

        String dvcId = deviceDelete.getDvcId();
        deviceService.deleteDevice(dvcId);
        return "success";
    }
}
