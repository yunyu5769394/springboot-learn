package com.lee.test.dao;

import com.lee.test.domain.Users;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserDao extends BaseMapper<Users> {
    Users findByUserName(String userName);
}
