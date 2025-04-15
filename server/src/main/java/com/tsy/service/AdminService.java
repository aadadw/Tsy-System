package com.tsy.service;

import com.tsy.dto.AdminUserUpdateDTO;

public interface AdminService {
    /**
     * 管理员修改用户信息
     * @param dto
     */
    void updateUserWithRoleSwitch(AdminUserUpdateDTO dto);
}
