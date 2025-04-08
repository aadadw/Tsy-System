package com.tsy.service.impl;

import com.sun.tools.attach.AgentLoadException;
import com.tsy.constant.MessageConstant;
import com.tsy.constant.StatusConstant;
import com.tsy.dto.LoginRequestDTO;
import com.tsy.entity.UserBase;
import com.tsy.exception.AccountLockedException;
import com.tsy.exception.AccountNotFoundException;
import com.tsy.exception.PasswordErrorException;
import com.tsy.mapper.UserMapper;
import com.tsy.service.AuthService;
import com.tsy.vo.LoginResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;
    /**
     * userLogin
     * @param loginRequestDTO
     * @return
     */
    @Override
    public UserBase login(LoginRequestDTO loginRequestDTO) {
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();
        //查询数据库
        UserBase userBase = userMapper.getByUsername(username);
        if (userBase ==null){
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码比对(MD5
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(userBase.getPassword())){
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if(userBase.getStatus()== StatusConstant.DISABLE){
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        return userBase;
    }
}
