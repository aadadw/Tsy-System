package com.tsy.service;

import com.tsy.dto.LoginRequestDTO;
import com.tsy.dto.RegisterDTO;
import com.tsy.entity.UserBase;
import com.tsy.vo.LoginResponseVO;

public interface AuthService {
    /**
     * 用户登录
     * @param loginRequestDTO
     * @return
     */
    public UserBase login(LoginRequestDTO loginRequestDTO);

    /**、
     * 用户注册
     * @param registerDTO
     */
    void add(RegisterDTO registerDTO);
}
