package com.roxy.blog.dao;

import com.roxy.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层类
 * @author roxy
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User getUserByName(@Param("username") String username);
}
