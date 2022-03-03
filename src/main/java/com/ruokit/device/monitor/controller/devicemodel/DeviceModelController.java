package com.ruokit.device.monitor.controller.devicemodel;

import com.ruokit.device.monitor.model.data.MfgCom;
import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import com.ruokit.device.monitor.service.mfgcom.MfgComService;
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
@RequestMapping("/view/device-model")
public class DeviceModelController {

    @Autowired
    MfgComService mfgComService;

    // view path
    private static String viewPrefix = "/device-model";
    private static String serviceViewName = "device-model";

    private final static Logger logger = LoggerFactory.getLogger(DeviceModelController.class);

    @GetMapping("/list")
    public String read(HttpServletRequest request, Model model) {
        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/list";
    }

    @GetMapping("/insert")
    public String insert(HttpServletRequest request, Model model) throws Exception {

        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");
        String cmpnId = loginUser.getCmpnId();

        MfgCom mfgCom = mfgComService.getMfgCom(cmpnId);
        if(mfgCom != null) {
            model.addAttribute("mfgComId",mfgCom.getMfgComId());
        }
        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/insert";
    }

    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {
        String dvcMdlId = request.getParameter("dvcMdlId");
        model.addAttribute("dvcMdlId",dvcMdlId);
        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/detail";
    }

}
