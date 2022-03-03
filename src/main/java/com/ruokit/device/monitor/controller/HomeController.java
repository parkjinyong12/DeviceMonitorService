package com.ruokit.device.monitor.controller;

import com.ruokit.device.monitor.repository.CmpnRepository;
import com.ruokit.device.monitor.service.common.session.CommonSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private CommonSessionService commonSessionService;

    // view path
    private static String viewPrefix = "/main";

    @GetMapping("/main")
    public String home(HttpServletRequest req) {

        // session에 login 정보를 담는다.
        commonSessionService.setUserInfo(req);
        commonSessionService.setMenuList(req);

        return viewPrefix + "/home";
    }
}
