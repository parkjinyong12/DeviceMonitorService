package com.ruokit.device.spring.intercept;

import com.ruokit.device.monitor.model.service.user.LoginUserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession httpSession = request.getSession();
        LoginUserSession loingUser = (LoginUserSession) httpSession.getAttribute("loginUser");

        if(loingUser == null) {
            logger.info("Login info does not exist. request url: " + request.getRequestURL().toString());
            response.sendRedirect("/login");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // System.out.println("postHandle1");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // System.out.println("afterCompletion1");
    }
}
