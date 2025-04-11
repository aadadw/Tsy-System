package com.tsy.mapper;

import com.tsy.entity.UserBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {
    /**
     * 通过用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from user_base where username =#{username}")
    UserBase getByUsername(String username);

    /**
     * 新增用户表基础信息
     * @param userBase
     */
//    @Insert("insert into user_base (username, password, email, role, status, create_time, phone) " +
//            "VALUES (#{username},#{password},#{email},#{role},#{status},#{createTime},#{phome})")
    void insert(UserBase userBase);
}
