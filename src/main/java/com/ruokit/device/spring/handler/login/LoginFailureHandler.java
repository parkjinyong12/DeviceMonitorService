package com.ruokit.device.spring.handler.login;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruokit.device.monitor.common.code.CommonCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {

  private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    String errorCode = "";
    if(exception instanceof BadCredentialsException) {
      errorCode = CommonCode.VIEW_LOGIN_FAIL;
    } else {
      errorCode = CommonCode.VIEW_UNKNOWN_ERROR;
    }
    logger.info("login failure handler passing");
    response.sendRedirect("/login?errorCode="+ errorCode);
  }
}
