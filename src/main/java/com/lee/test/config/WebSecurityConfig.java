package com.lee.test.config;

import com.lee.test.service.impl.MyFilterSecurityInterceptorImpl;
import com.lee.test.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

/**
 * @Author : Leason
 * @Create : 2018-09-19 17:17
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyFilterSecurityInterceptorImpl myFilterSecurityInterceptor;

    @Autowired
    private UserDetailsService customUserService;

//    @Bean
//    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
//        return new CustomUserServiceImpl();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService); //user Details Service验证
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new PasswordEncoder(){

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
            }}); //user Details Service验证
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                // 设置静态的资源允许所有访问
                .antMatchers("/css/**").permitAll()
                // 其他所有资源都需要登录后才能访问
                .anyRequest().authenticated()
                // 设置默认登录界面/login
                .and().formLogin().loginPage("/login")
                // 强制指定登录成功后跳转的路径
                .successHandler(new ForwardAuthenticationSuccessHandler("/"))
                .failureUrl("/login?error")
                .permitAll() //登录页面用户任意访问
                // 设置登出的路径和等处成功后访问的路径
                .and().logout().permitAll(); //注销行为任意访问
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
}
