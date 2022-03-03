package com.ruokit.device.spring.config;

import com.ruokit.device.spring.entrypoint.RestAuthenticationEntryPoint;
import com.ruokit.device.spring.handler.login.LoginFailureHandler;
import com.ruokit.device.spring.handler.login.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security 인증(Authentication) 처리를 진행합니다.
 *
 * @author jinyong.park
 */
@Configuration
@EnableWebSecurity
public class DeviceMonitorSecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 전역 보안 설정. 리소스 무시 등등
     */
    @Override
    public void configure(WebSecurity auth) throws Exception {
        auth.ignoring().antMatchers("/css/**","/js/**","/plugins/**");
    }

    /**
     * 로그인 인증
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /*
     * URL을 통한 접근 인증
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

//        http.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());
        http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll();
        http.formLogin().loginPage("/login")
                .loginProcessingUrl("/login_process").usernameParameter("username").passwordParameter("password")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler());

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }

    /*
     * 로그인 인증 설정
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }
}
