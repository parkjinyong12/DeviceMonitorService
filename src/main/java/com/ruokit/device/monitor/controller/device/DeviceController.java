package com.ruokit.device.monitor.controller.device;

import com.ruokit.device.monitor.common.code.CommonCode;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.service.device.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/view/device")
public class DeviceController {

    // view path
    private static String viewPrefix = "/device";
    private static String serviceViewName = "device";

    @Autowired
    private DeviceService deviceService;

    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

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
        // 유심 장착상태는 초기에 미장착으로 한다.
        model.addAttribute("hndsetMntSt", CommonCode.HNDSET_MOUNT_STATUS_UNMOUNT);

        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/insert";
    }

    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {
        String dvcId = request.getParameter("dvcId");
        String isUpdate = request.getParameter("isUpdate");
        model.addAttribute("isUpdate",isUpdate);
        model.addAttribute("dvcId",dvcId);
        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/detail";
    }
}
