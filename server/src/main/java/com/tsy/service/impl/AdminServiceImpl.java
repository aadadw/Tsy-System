package com.tsy.service.impl;

import com.tsy.dto.AdminUserUpdateDTO;
import com.tsy.entity.CoachInfo;
import com.tsy.entity.UserInfo;
import com.tsy.mapper.AuthMapper;
import com.tsy.mapper.CoachMapper;
import com.tsy.mapper.UserMapper;
import com.tsy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AuthMapper authMapper;

    /**
     * 管理员修改用户信息
     * @param dto
     */
    @Override
    public void updateUserWithRoleSwitch(AdminUserUpdateDTO dto) {
        authMapper.updateBaseFields(dto);


    }
}
