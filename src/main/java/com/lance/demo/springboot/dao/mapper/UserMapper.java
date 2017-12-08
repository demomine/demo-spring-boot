package com.lance.demo.springboot.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lance.demo.springboot.config.Pagination;
import com.lance.demo.springboot.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where name=#{name}")
    User selectByName(String name);

    @Select("select * from user where age = #{age}")
    List<User> selectUserList(Pagination pagination, Integer age);

}
