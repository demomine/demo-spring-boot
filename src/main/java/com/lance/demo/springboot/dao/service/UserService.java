package com.lance.demo.springboot.dao.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lance.demo.springboot.dao.entity.User;
import com.lance.demo.springboot.dao.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserService extends ServiceImpl<UserMapper,User>{
    public User selectByName(String name) {
        return baseMapper.selectByName(name);
    }
}
