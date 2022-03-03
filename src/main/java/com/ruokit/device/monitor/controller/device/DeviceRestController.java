package com.ruokit.device.monitor.controller.device;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.Device;
import com.ruokit.device.monitor.model.data.PageView;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.device.DeviceRegist;
import com.ruokit.device.monitor.model.view.install.DvcIslRegist;
import com.ruokit.device.monitor.service.device.DeviceService;
import com.ruokit.device.monitor.common.service.PagingService;
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
@RequestMapping("/data/device")
public class DeviceRestController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DvcIslService dvcIslService;

    @Autowired
    private PagingService pagingService;

    private static Logger logger = LoggerFactory.getLogger(DeviceRestController.class);

    @GetMapping("/page")
    public PageView getPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String cmpnId = loginUser.getCmpnId();

        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<DeviceDetail> deviceDetailPage = deviceService.getDeviceList(cmpnId, pageRequest);

        return pagingService.getPaging(deviceDetailPage);
    }

    @PostMapping("/insert")
    public DeviceDetail insert(Model model, HttpServletRequest request, @RequestBody DeviceRegist deviceRegist) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String loginId = loginUser.getLoginId();

        // 로그인 사용자를 디바이스 등록시 등록자로 저장한다.
        deviceRegist.setRegId(loginId);
        Device device = deviceService.insertDevice(deviceRegist);

        // 디바이스 설치 히스토리 저장
        if(device != null) {
            DvcIslRegist dvcIslRegist = new DvcIslRegist();
            dvcIslRegist.setDvcId(String.valueOf(device.getDvcId()));
            dvcIslRegist.setRegUserId(loginId);
            dvcIslRegist.setDvcIslSt(CommonCode.DEVICE_INSTALL_STATUS_REGIST);
            dvcIslService.insertDvcIsl(dvcIslRegist);

            // device에서도 최근 설치이력을 저장한다.
            device.setLstDvcIslSt(CommonCode.DEVICE_INSTALL_STATUS_REGIST);
        }
        DeviceDetail deviceDetail = new DeviceDetail();
        deviceDetail.setDvcId(String.valueOf(device.getDvcId()));

        return deviceDetail;
    }

    @PostMapping("/detail")
    public DeviceDetail detail(@RequestBody DeviceDetail deviceDetail) throws Exception {

        String dvcId = deviceDetail.getDvcId();
        return deviceService.getDeviceDetail(dvcId);
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
