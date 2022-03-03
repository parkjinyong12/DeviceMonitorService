package com.ruokit.device.spring.config;

import com.ruokit.device.spring.intercept.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DeviceMonitorWebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> urlPatterns = Arrays.asList("/view/**", "/data/**", "/main");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(urlPatterns)
                .excludePathPatterns("/login");
    }
}
