package com.tsy.service;

import com.tsy.dto.UserPageQueryDTO;
import com.tsy.result.PageResult;

public interface UserService {
    /**
     * 根据userid删除普通用户数据
     * @param userId
     */
     void deleteByUserId(Long userId) ;

    /**
     * 分页查询用户数据
     * @param userPageQueryDTO
     * @return
     */
    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);
}
