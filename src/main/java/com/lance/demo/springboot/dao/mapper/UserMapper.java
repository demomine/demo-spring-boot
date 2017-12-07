package com.lance.demo.springboot.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lance.demo.springboot.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where name=#{name}")
    User selectByName(String name);
}
