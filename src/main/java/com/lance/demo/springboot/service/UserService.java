package com.lance.demo.springboot.service;

import com.lance.demo.springboot.User;
import com.lance.demo.springboot.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(String id) {
        log.info("user id: {}",id);
        return userMapper.getUser(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
