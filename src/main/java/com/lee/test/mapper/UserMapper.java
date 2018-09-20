package com.lee.test.mapper;

import com.lee.test.domain.SwaggerUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * t_user 操作：演示两种方式
 * <p>第一种是基于mybatis3.x版本后提供的注解方式<p/>
 * <p>第二种是早期写法，将SQL写在 XML 中<p/>
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<SwaggerUser> {


    /**
     * 使用注解方式
     * 根据用户名查询用户结果集
     *
     * @param username 用户名
     * @return 查询结果
     */
    @Select("SELECT * FROM user WHERE username = #{username}")
    List<SwaggerUser> findByUsername(@Param("username") String username);


    /**
     * 使用xml方式
     * 保存用户信息
     *
     * @param swaggerUser 用户信息
     * @return 成功 1 失败 0
     */
    int addUser(SwaggerUser swaggerUser);

    /**
     * 根据用户名统计（TODO 假设它是一个很复杂的SQL）
     *
     * @param username 用户名
     * @return 统计结果
     */
    int countByUsername(String username);
}
