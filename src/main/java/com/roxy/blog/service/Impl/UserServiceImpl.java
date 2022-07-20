package com.roxy.blog.service.Impl;

import com.roxy.blog.dao.UserMapper;
import com.roxy.blog.entity.User;
import com.roxy.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getUserByName(String username) {
        User user = userMapper.getUserByName(username);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean checkUser(User user) {
        return checkUser(user.getUsername(), user.getPassword());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean checkUser(String username, String password) {
        User user = getUserByName(username);
        if(user == null || !user.getPassword().equals(password)){
            return false;
        }
        return true;
    }
}
