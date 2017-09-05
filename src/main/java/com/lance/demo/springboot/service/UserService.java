package com.lance.demo.springboot.service;

import com.lance.demo.springboot.User;
import com.lance.demo.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
