package com.tsy.mapper;

import com.tsy.entity.UserBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 通过用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from user_base where username =#{username}")
    UserBase getByUsername(String username);
}
