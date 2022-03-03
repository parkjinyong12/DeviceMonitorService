package com.ruokit.device.monitor.controller.devicemodel;

import com.ruokit.device.monitor.model.data.DeviceModel;
import com.ruokit.device.monitor.model.data.PageView;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.model.view.devicemodel.DeviceModelDetail;
import com.ruokit.device.monitor.model.view.devicemodel.DeviceModelRegist;
import com.ruokit.device.monitor.service.devicemodel.DeviceModelService;
import com.ruokit.device.monitor.common.service.PagingService;
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
import java.util.List;

@RestController
@RequestMapping("/data/device-model")
public class DeviceModelRestController {

    @Autowired
    private DeviceModelService dvcMdlService;

    @Autowired
    private PagingService pagingService;

    private static Logger logger = LoggerFactory.getLogger(DeviceModelRestController.class);

    @GetMapping("/page")
    public PageView getPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String cmpnId = loginUser.getCmpnId();

        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<DeviceModelDetail> dvcMdlDetailPage = dvcMdlService.getDeviceModelList(cmpnId, pageRequest);

        return pagingService.getPaging(dvcMdlDetailPage);
    }

    @PostMapping("/combo")
    public List<DeviceModelDetail> comboMfgcom(@RequestBody DeviceModelDetail dvcMdlDetail) throws Exception {

        String mfgComId = dvcMdlDetail.getMfgComId();
        return dvcMdlService.getDeviceModelList(mfgComId);
    }

    @PostMapping("/insert")
    public String insert(Model model, HttpServletRequest request, @RequestBody DeviceModelRegist dvcMdlRegist) throws Exception {

        dvcMdlService.insertDeviceModel(dvcMdlRegist);
        return "success";
    }

    @PostMapping("/detail")
    public DeviceModelDetail detail(@RequestBody DeviceModelDetail dvcMdlDetail) throws Exception {

        // 디바이스 모델id
        String dvcMdlId = dvcMdlDetail.getDvcMdlId();
        DeviceModelDetail result = new DeviceModelDetail();
        // 디바이스 모델 검색
        DeviceModel dvcMdl = dvcMdlService.getDeviceModelDetail(dvcMdlId);
        result.setDvcMdlId(String.valueOf(dvcMdl.getDvcMdlId()));
        result.setDvcMdlNm(dvcMdl.getDvcMdlNm());
        result.setMfgComId(String.valueOf(dvcMdl.getMfgComId()));

        return result;
    }

    @PostMapping("/update")
    public String update(Model model, HttpServletRequest request, @RequestBody DeviceModelDetail dvcMdlUpdate) throws Exception {

        dvcMdlService.updateDeviceModel(dvcMdlUpdate);
        return "success";
    }

    @PostMapping("/delete")
    public String delete(Model model, HttpServletRequest request, @RequestBody DeviceModelDetail dvcMdlDelete) throws Exception {

        dvcMdlService.deleteDeviceModel(dvcMdlDelete);
        return "success";
    }
}
