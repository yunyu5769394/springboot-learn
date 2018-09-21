package com.lee.test.controller;


import com.lee.test.dao.UserDao;
import com.lee.test.domain.Msg;
import com.lee.test.domain.Users;
import com.lee.test.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Author : Leason
 * @Create : 2018-09-20 10:53
 **/
@Controller
public class HomeController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/login")
    public String index(){
        return "login";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }

    @RequestMapping("/adduser")
    @ResponseBody
    public String adduser(){
        String userName = "admin";
        String passWord = "admin";
        passWord = MD5Util.encode(passWord);
        Users user = new Users();
        user.setUsername(userName);
        user.setPassword(passWord);
        userDao.addUser(user);
        return "123";
    }
}
