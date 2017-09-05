package com.lance.demo.springboot.mapper;

import com.lance.demo.springboot.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from T_USER where id = #{id}")
    User getUser(@Param("id") String id);

    @Update("update t_user set name = #{name} where id = #{id}")
    void updateUser(User user);
}
