package com.ruokit.device.monitor.controller.installconfirm;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.data.PageView;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.install.DvcIslDetail;
import com.ruokit.device.monitor.model.view.install.DvcIslRegist;
import com.ruokit.device.monitor.common.service.PagingService;
import com.ruokit.device.monitor.service.device.DeviceService;
import com.ruokit.device.monitor.service.installconfirm.DvcIslConfirmService;
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
@RequestMapping("/data/install-confirm")
public class dvcIslConfirmRestController {

    @Autowired
    private DvcIslConfirmService dvcIslConfirmService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PagingService pagingService;

    private static Logger logger = LoggerFactory.getLogger(dvcIslConfirmRestController.class);

    @GetMapping("/page")
    public PageView getPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");

        String cmpnId = loginUser.getCmpnId();

        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<DeviceDetail> deviceDetailPage = dvcIslConfirmService.getDeviceList(cmpnId, pageRequest);

        return pagingService.getPaging(deviceDetailPage);
    }

    @GetMapping("/detail")
    public PageView getDetailPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        String dvcId = request.getParameter("dvcId");

        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<DvcIslDetail> dvcIslDetailPage = dvcIslConfirmService.getDvcIslList(dvcId, pageRequest);

        return pagingService.getPaging(dvcIslDetailPage);
    }

    @PostMapping("/request")
    public String request(Model model, HttpServletRequest request, @RequestBody DvcIslRegist dvcIslRegist) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String loginId = loginUser.getLoginId();

        dvcIslRegist.setDvcId(dvcIslRegist.getDvcId());
        dvcIslRegist.setDvcIslSt(CommonCode.DEVICE_INSTALL_STATUS_REQUEST);
        dvcIslRegist.setRegUserId(loginId);
        dvcIslConfirmService.insertDvcIsl(dvcIslRegist);

        return "success";
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
