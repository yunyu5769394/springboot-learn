package com.lee.test.service;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public interface MyInvocationSecurityMetadataSourceService extends FilterInvocationSecurityMetadataSource {
    void loadResourceDefine();
}
