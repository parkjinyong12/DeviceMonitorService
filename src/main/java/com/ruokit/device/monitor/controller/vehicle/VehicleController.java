package com.ruokit.device.monitor.controller.vehicle;

import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.service.device.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view/vehicle")
public class VehicleController {

    // view path
    private static String viewPrefix = "/vehicle";
    private static String serviceViewName = "vehicle";

    @Autowired
    private DeviceService deviceService;

    private final static Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {

        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/list";
    }

    @GetMapping("/insert")
    public String insert(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");

        String isContinue = request.getParameter("isContinue");
        model.addAttribute("isContinue",isContinue);

        model.addAttribute("cmpnId", loginUser.getCmpnId());
        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/insert";
    }

    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {
        String vehicleId = request.getParameter("vehicleId");
        model.addAttribute("vehicleId",vehicleId);
        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/detail";
    }
}
