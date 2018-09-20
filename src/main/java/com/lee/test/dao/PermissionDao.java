package com.lee.test.dao;

import com.lee.test.domain.Permission;

import java.util.List;

public interface PermissionDao {
    List<Permission> findAll();
    List<Permission> findByAdminUserId(int userId);
}
