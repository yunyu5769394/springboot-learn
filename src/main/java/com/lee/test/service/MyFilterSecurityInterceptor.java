package com.lee.test.service;

import org.springframework.security.web.FilterInvocation;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import java.io.IOException;

public interface MyFilterSecurityInterceptor extends Filter {
    void invoke(FilterInvocation fi) throws IOException, ServletException;
}
