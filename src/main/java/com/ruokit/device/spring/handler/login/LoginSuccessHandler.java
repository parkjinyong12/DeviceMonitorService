package com.ruokit.device.spring.handler.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ruokit.device.monitor.model.data.Company;
import com.ruokit.device.monitor.model.data.UserInfo;
import com.ruokit.device.monitor.model.service.user.LoginUserModel;
import com.ruokit.device.monitor.repository.CmpnRepository;
import com.ruokit.device.monitor.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

  private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

  @Override
  public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
      Authentication auth) throws IOException, ServletException {
    logger.info("onAuthenticationSuccess() in LoginSuccessHandler");
    HttpSession session = req.getSession();

    LoginUserModel loginUser = (LoginUserModel) auth.getPrincipal();
    session.setAttribute("loginId", loginUser.getUsername());
    session.setAttribute("loginInfo", loginUser);

    // ROLE에 따라 어느 화면으로 보낼지 선택할 수 있다.

    resp.sendRedirect("/main");
  }
}
