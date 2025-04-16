package com.tsy.service.impl;

import com.tsy.constant.MessageConstant;
import com.tsy.constant.StatusConstant;
import com.tsy.dto.LoginRequestDTO;
import com.tsy.dto.RegisterDTO;
import com.tsy.entity.CoachInfo;
import com.tsy.entity.UserBase;
import com.tsy.entity.UserInfo;
import com.tsy.exception.AccountLockedException;
import com.tsy.exception.AccountNotFoundException;
import com.tsy.exception.PasswordErrorException;
import com.tsy.mapper.AuthMapper;
import com.tsy.mapper.CoachMapper;
import com.tsy.mapper.UserMapper;
import com.tsy.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CoachMapper coachMapper;
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
        UserBase userBase = authMapper.getByUsername(username);
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

    /**
     * 用户注册
     * @param registerDTO
     */
    @Override
    @Transactional
    public void register(RegisterDTO registerDTO) {
        //插入基本信息
        UserBase  userBase = new UserBase();
        BeanUtils.copyProperties(registerDTO,userBase);
        //MD5存入密码进行加密
        userBase.setPassword(DigestUtils.md5DigestAsHex(userBase.getPassword().getBytes()));
        //设置一些默认数据
        userBase.setStatus(1);//默认启用
        userBase.setCreateTime(LocalDateTime.now());
        log.info("插入基本用户数据，userbase：{}",userBase);
        authMapper.insert(userBase);
        Long id = userBase.getId();//回填id
        //插入拓展信息：判断一下role（user，coach
        String role =registerDTO.getRole();
        if("user".equals(role)){
            //是普通用户
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(registerDTO,userInfo);
            userInfo.setUserId(id);
            log.info("插入普通用户数据：{}",userInfo);
            userMapper.insert(userInfo);

        } else if ("coach".equals(role)) {
            //是教练
            CoachInfo coachInfo = new CoachInfo();
            BeanUtils.copyProperties(registerDTO,coachInfo);
            coachInfo.setUserId(id);
            log.info("插入教练数据:{}",coachInfo);
            coachMapper.insert(coachInfo);
        }
    }
}
