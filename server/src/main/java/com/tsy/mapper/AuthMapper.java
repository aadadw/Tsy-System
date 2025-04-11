package com.tsy.mapper;

import com.tsy.entity.UserBase;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {
    /**
     * 删除info表后删除对应的主表数据
     * @param id
     */
    @Delete("delete from user_base where id=#{id}")
    void deleteById(Long id) ;


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
