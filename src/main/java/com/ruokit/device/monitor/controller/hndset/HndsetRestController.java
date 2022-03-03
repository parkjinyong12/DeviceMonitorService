package com.ruokit.device.monitor.controller.hndset;

import com.ruokit.device.monitor.model.data.HndsetOpen;
import com.ruokit.device.monitor.model.data.PageView;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.model.view.hndset.HndsetDetail;
import com.ruokit.device.monitor.model.view.hndset.HndsetOpenDetail;
import com.ruokit.device.monitor.model.view.hndset.HndsetOpenRegist;
import com.ruokit.device.monitor.model.view.hndset.HndsetRegist;
import com.ruokit.device.monitor.common.service.PagingService;
import com.ruokit.device.monitor.model.view.vehicle.VehicleDetail;
import com.ruokit.device.monitor.service.device.DeviceService;
import com.ruokit.device.monitor.service.hndset.HndsetService;
import com.ruokit.device.monitor.service.vehicle.VehicleService;
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
@RequestMapping("/data/hndset")
public class HndsetRestController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private HndsetService hndsetService;

    @Autowired
    private PagingService pagingService;

    private static Logger logger = LoggerFactory.getLogger(HndsetRestController.class);

    @GetMapping("/page")
    public PageView getPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String cmpnId = loginUser.getCmpnId();

        String hndsetNum = request.getParameter("searchText");
        PageRequest pageRequest = pagingService.getPageRequest(request);

        Page<HndsetDetail> hndsetDetailPage = null;
        if(hndsetNum == null || hndsetNum.length() == 0) {
            hndsetDetailPage = hndsetService.getHndsetList(cmpnId, pageRequest);
        } else {
            hndsetDetailPage = hndsetService.getHndsetList(cmpnId, hndsetNum, pageRequest);
        }
        return pagingService.getPaging(hndsetDetailPage);
    }

    @GetMapping("/page/usable")
    public PageView getUsablePage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String cmpnId = loginUser.getCmpnId();

        String hndsetNum = request.getParameter("searchText");
        PageRequest pageRequest = pagingService.getPageRequest(request);

        Page<HndsetDetail> hndsetDetailPage = null;
        if(hndsetNum == null || hndsetNum.length() == 0) {
            hndsetDetailPage = hndsetService.getUsableHndsetList(cmpnId, pageRequest);
        } else {
            hndsetDetailPage = hndsetService.getUsableHndsetList(cmpnId, hndsetNum, pageRequest);
        }
        return pagingService.getPaging(hndsetDetailPage);
    }

    @GetMapping("/page/open-hist")
    public PageView getOpenHistPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        String hndsetId = request.getParameter("hndsetId");
        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String cmpnId = loginUser.getCmpnId();

        PageRequest pageRequest = pagingService.getPageRequest(request);
        Page<HndsetOpenDetail> hndsetDetailPage = hndsetService.getHndsetOpenList(hndsetId, pageRequest);
        return pagingService.getPaging(hndsetDetailPage);
    }

    @PostMapping("/insert")
    public String insert(Model model, HttpServletRequest request, @RequestBody HndsetRegist hndsetRegist) throws Exception {
        hndsetService.insertHndset(hndsetRegist);
        return "success";
    }

    @PostMapping("/insert/open-hist")
    public String insertOpenHist(Model model, HttpServletRequest request, @RequestBody HndsetOpenRegist hndsetOpenRegist) throws Exception {
        hndsetService.insertHndsetOpen(hndsetOpenRegist);
        return "success";
    }

    @PostMapping("/detail")
    public HndsetDetail detail(@RequestBody HndsetDetail hndsetDetail) throws Exception {
        String hndsetId = hndsetDetail.getHndsetId();
        return hndsetService.getHndsetDetail(hndsetId);
    }

    @PostMapping("/update")
    public String update(Model model, HttpServletRequest request, @RequestBody HndsetDetail hndsetOpenUpdate) throws Exception {
        hndsetService.updateHndset(hndsetOpenUpdate);
        return "success";
    }

    @PostMapping("/delete")
    public String delete(Model model, HttpServletRequest request, @RequestBody HndsetDetail hndsetOpenDelete) throws Exception {

        String hndsetId = hndsetOpenDelete.getHndsetId();
        hndsetService.deleteHndset(hndsetId);
        return "success";
    }
}
