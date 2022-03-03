package com.ruokit.device.monitor.controller.vehicle;

import com.ruokit.device.monitor.model.data.PageView;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.model.view.vehicle.VehicleDetail;
import com.ruokit.device.monitor.model.view.vehicle.VehicleRegist;
import com.ruokit.device.monitor.common.service.PagingService;
import com.ruokit.device.monitor.service.device.DeviceService;
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
@RequestMapping("/data/vehicle")
public class VehicleRestController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private PagingService pagingService;

    private static Logger logger = LoggerFactory.getLogger(VehicleRestController.class);

    @GetMapping("/page")
    public PageView getPage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");

        String cmpnId = loginUser.getCmpnId();

        String vehicleNum = request.getParameter("searchText");
        PageRequest pageRequest = pagingService.getPageRequest(request);

        Page<VehicleDetail> vehicleDetailPage = null;
        if(vehicleNum == null || vehicleNum.length() == 0) {
            vehicleDetailPage = vehicleService.getVehicleList(cmpnId, pageRequest);
        } else {
            vehicleDetailPage = vehicleService.getVehicleList(cmpnId, vehicleNum, pageRequest);
        }

        return pagingService.getPaging(vehicleDetailPage);
    }

    @GetMapping("/page/usable")
    public PageView getUsablePage(HttpServletResponse response, Model model, HttpServletRequest request, Pageable pageable) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");

        String cmpnId = loginUser.getCmpnId();

        String vehicleNum = request.getParameter("searchText");
        PageRequest pageRequest = pagingService.getPageRequest(request);

        Page<VehicleDetail> vehicleDetailPage = null;
        if(vehicleNum == null || vehicleNum.length() == 0) {
            vehicleDetailPage = vehicleService.getUsableVehicleList(cmpnId, pageRequest);
        } else {
            vehicleDetailPage = vehicleService.getUsableVehicleList(cmpnId, vehicleNum, pageRequest);
        }
        return pagingService.getPaging(vehicleDetailPage);
    }

    @PostMapping("/insert")
    public String insert(Model model, HttpServletRequest request, @RequestBody VehicleRegist vehicleRegist) throws Exception {

        vehicleService.insertVehicle(vehicleRegist);
        return "success";
    }

    @PostMapping("/detail")
    public VehicleDetail detail(@RequestBody VehicleDetail vehicleDetail) throws Exception {

        String vehicleId = vehicleDetail.getVehicleId();
        return vehicleService.getVehicleDetail(vehicleId);
    }

    @PostMapping("/update")
    public String update(Model model, HttpServletRequest request, @RequestBody VehicleDetail vehicleUpdate) throws Exception {

        vehicleService.updateVehicle(vehicleUpdate);
        return "success";
    }

    @PostMapping("/delete")
    public String delete(Model model, HttpServletRequest request, @RequestBody VehicleDetail vehicleDelete) throws Exception {

        String vehicleId = vehicleDelete.getVehicleId();
        vehicleService.deleteVehicle(vehicleId);
        return "success";
    }
}
