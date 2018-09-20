package com.lee.test.domain;

import java.util.List;

/**
 * @Author : Leason
 * @Create : 2018-09-19 17:42
 **/
public class Users {
    private Integer id;
    private String username;
    private String password;

    private List<Authorities> authorities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }
}
