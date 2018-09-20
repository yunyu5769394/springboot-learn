package com.lee.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lee.test.domain.SwaggerUser;
import com.lee.test.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(SpringBootMybatisApplicationTests.class);

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() throws Exception {
        final int row1 = userMapper.addUser(new SwaggerUser("u1", "p1"));
        log.info("[添加结果] - [{}]", row1);
        final int row2 = userMapper.addUser(new SwaggerUser("u2", "p2"));
        log.info("[添加结果] - [{}]", row2);
        final int row3 = userMapper.addUser(new SwaggerUser("u1", "p3"));
        log.info("[添加结果] - [{}]", row3);
        final List<SwaggerUser> u1 = userMapper.findByUsername("u1");
        log.info("[根据用户名查询] - [{}]", u1);
    }

    @Test
    public void test2() throws Exception {
        final SwaggerUser swaggerUser1 = new SwaggerUser("u1", "p1");
        final SwaggerUser swaggerUser2 = new SwaggerUser("u1", "p2");
        final SwaggerUser swaggerUser3 = new SwaggerUser("u3", "p3");
        userMapper.insertSelective(swaggerUser1);
        log.info("[user1回写主键] - [{}]", swaggerUser1.getId());
        userMapper.insertSelective(swaggerUser2);
        log.info("[user2回写主键] - [{}]", swaggerUser2.getId());
        userMapper.insertSelective(swaggerUser3);
        log.info("[user3回写主键] - [{}]", swaggerUser3.getId());
        final int count = userMapper.countByUsername("u1");
        log.info("[调用自己写的SQL] - [{}]", count);

        // TODO 模拟分页
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        userMapper.insertSelective(new SwaggerUser("u1", "p1"));
        // TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.userMapper.selectAll());
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<SwaggerUser> userPageInfo = new PageInfo<>(this.userMapper.selectAll());
        log.info("[普通写法] - [{}]", userPageInfo);
    }

}
