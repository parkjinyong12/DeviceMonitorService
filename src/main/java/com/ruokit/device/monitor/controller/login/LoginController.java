package com.ruokit.device.monitor.controller.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", request.getParameter("errorCode"));
        HttpSession httpSession = request.getSession();
        return "login/login";
    }

    @RequestMapping("/loginFail")
    public String getLoginFail() {
        return "login/loginFail";
    }

    @RequestMapping("/loginSuccess")
    public String getLoginSuccess() {
        return "main/home";
    }
}
