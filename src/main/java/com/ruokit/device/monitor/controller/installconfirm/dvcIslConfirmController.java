package com.ruokit.device.monitor.controller.installconfirm;

import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view/install-confirm")
public class dvcIslConfirmController {

    // view path
    private static String viewPrefix = "/install-confirm";
    private static String serviceViewName = "install-confirm";

    private final static Logger logger = LoggerFactory.getLogger(dvcIslConfirmController.class);

    @GetMapping("/list")
    public String list(HttpServletRequest request, Model model) {

        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/list";
    }

    @GetMapping("/insert")
    public String insert(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        LoginUserSession loginUser = (LoginUserSession) session.getAttribute("loginUser");

        model.addAttribute("cmpnId", loginUser.getCmpnId());
        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/insert";
    }

    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {
        String dvcId = request.getParameter("dvcId");
        model.addAttribute("dvcId",dvcId);
        model.addAttribute("serviceViewName",serviceViewName);
        return viewPrefix + "/detail";
    }
}
