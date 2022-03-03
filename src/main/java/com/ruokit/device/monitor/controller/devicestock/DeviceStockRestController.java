package com.ruokit.device.monitor.controller.devicestock;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.common.service.PagingService;
import com.ruokit.device.monitor.model.data.Device;
import com.ruokit.device.monitor.model.data.DeviceModel;
import com.ruokit.device.monitor.model.data.DvcStk;
import com.ruokit.device.monitor.model.data.PageView;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.model.view.device.DeviceDetail;
import com.ruokit.device.monitor.model.view.device.DeviceRegist;
import com.ruokit.device.monitor.model.view.devicemodel.DeviceModelDetail;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkDetail;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkGroupDetail;
import com.ruokit.device.monitor.model.view.dvcstk.DvcStkRegist;
import com.ruokit.device.monitor.model.view.install.DvcIslRegist;
import com.ruokit.device.monitor.service.common.session.CommonSessionService;
import com.ruokit.device.monitor.service.device.DeviceService;
import com.ruokit.device.monitor.service.devicemodel.DeviceModelService;
import com.ruokit.device.monitor.service.dvcstk.DvcStkService;
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
import java.util.List;

@RestController
@RequestMapping("/data/device-stock")
public class DeviceStockRestController {

    @Autowired
    private DvcStkService dvcStkService;

    @Autowired
    private DeviceModelService dvcMdlService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PagingService pagingService;

    @Autowired
    private CommonSessionService commonSessionService;

    private static Logger logger = LoggerFactory.getLogger(DeviceStockRestController.class);

    @GetMapping("/page/device-model")
    public PageView getDeviceModelGroupPage(HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String cmpnId = loginUser.getCmpnId();

        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<DvcStkGroupDetail> dvcStkDetailPage = dvcStkService.getDeviceStockGroupList(cmpnId, pageRequest);

        return pagingService.getPaging(dvcStkDetailPage);
    }

    @GetMapping("/page/device-model/detail")
    public PageView getDeviceModelDetailPage(HttpServletRequest request) throws Exception {

        LoginUserSession loginUser = commonSessionService.getLoginUser(request);
        String cmpnId = loginUser.getCmpnId();

        String dvcMdlId = request.getParameter("dvcMdlId");
        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<DvcStkDetail> dvcStkDetailPage = dvcStkService.getDeviceStockList(cmpnId, dvcMdlId, pageRequest);

        return pagingService.getPaging(dvcStkDetailPage);
    }

    @PostMapping("/insert/many")
    public List<DvcStk> insertMany(Model model, HttpServletRequest request, @RequestBody List<DvcStkRegist> regists) throws Exception {
        return dvcStkService.insertDeviceStocks(regists);
    }

    @PostMapping("/insert")
    public DvcStkDetail insert(Model model, HttpServletRequest request, @RequestBody DvcStkRegist regist) throws Exception {

        DvcStk dvcStk = dvcStkService.insertDeviceStock(regist);

        DvcStkDetail dvcStkDetail = new DvcStkDetail();
        dvcStkDetail.setDvcStkId(String.valueOf(dvcStk.getDvcStkId()));
        dvcStkDetail.setDvcMdlId(String.valueOf(dvcStk.getDvcMdlId()));

        return dvcStkDetail;
    }

    @PostMapping("/exist")
    public Boolean exist(Model model, HttpServletRequest request, @RequestBody DvcStkDetail dvcStkDetail) throws Exception {
        return dvcStkService.isExistDeviceStocks(dvcStkDetail);
    }

    @PostMapping("/detail")
    public DvcStkDetail detail(HttpServletRequest request, @RequestBody DvcStkDetail dvcStkDetail) throws Exception {

        LoginUserSession loginUser = commonSessionService.getLoginUser(request);
        String cmpnId = loginUser.getCmpnId();

        String dvcMdlId = dvcStkDetail.getDvcMdlId();
        DvcStkDetail result = new DvcStkDetail();

        // 디바이스 모델 검색
        DeviceModel dvcMdl = dvcMdlService.getDeviceModelDetail(dvcMdlId);
        result.setCmpnId(cmpnId);
        result.setDvcMdlId(String.valueOf(dvcMdl.getDvcMdlId()));
        result.setMfgComId(String.valueOf(dvcMdl.getMfgComId()));

        return result;
    }

    @PostMapping("/update")
    public String update(Model model, HttpServletRequest request, @RequestBody DvcStkDetail update) throws Exception {
        dvcStkService.updateDeviceStock(update);
        return "success";
    }

    @PostMapping("/delete")
    public String delete(Model model, HttpServletRequest request, @RequestBody DeviceDetail deviceDelete) throws Exception {

        String dvcId = deviceDelete.getDvcId();
        deviceService.deleteDevice(dvcId);
        return "success";
    }
}
