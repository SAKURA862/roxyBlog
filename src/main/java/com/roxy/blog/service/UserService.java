package com.roxy.blog.service;

import com.roxy.blog.entity.User;

/**
 * 用户相关操作类
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户
     */
    User getUserByName(String username);

    /**
     * 查询用户是否存在
     * @param user 待查询用户
     * @return 是否存在
     */
    boolean checkUser(User user);

    /**
     *查询用户是否存在
     * @param username 待查询用户名
     * @param password 待查询用户密码
     * @return 是否存在
     */
    boolean checkUser(String username, String password);
}
