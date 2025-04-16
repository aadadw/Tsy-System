package com.tsy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tsy.dto.UserPageQueryDTO;
import com.tsy.dto.UserRegisterDTO;
import com.tsy.entity.UserBase;
import com.tsy.entity.UserInfo;
import com.tsy.mapper.AuthMapper;
import com.tsy.mapper.UserMapper;
import com.tsy.result.PageResult;
import com.tsy.service.UserService;
import com.tsy.vo.UserFullVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthMapper authMapper;
    /**
     * 根据userid删除数据
     * @param userId
     */
    @Override
    @Transactional
    public void deleteByUserId(Long userId) {
        //这里必须先删除外联表info才能删除主表base要不报错
        userMapper.deleteByUserId(userId);
        authMapper.deleteById(userId);
    }

    /**
     * 用户分页查询
     * @param dto
     * @return
     */
    @Override
    public PageResult pageQuery(UserPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<UserFullVO> voList = userMapper.pageQuery(dto);
        Page<UserFullVO> page = (Page<UserFullVO>) voList;
        return new PageResult(page.getTotal(), page.getResult());
    }

//    /**
//     * 普通用户注册
//     * @param userRegisterDTO
//     */
//    @Override
//    @Transactional
//    public void register(UserRegisterDTO userRegisterDTO) {
//        UserBase userBase = new UserBase();
//        UserInfo userInfo = new UserInfo();
//        LocalDateTime time = LocalDateTime.now();
//        BeanUtils.copyProperties(userRegisterDTO,userBase);
//        BeanUtils.copyProperties(userRegisterDTO,userInfo);
//        //补全基本信息
//        userBase.setCreateTime(time);
//        userBase.setStatus(1);
//        userInfo.setCreateTime(time);
//        userInfo.setUpdateTime(time);
//        //先写入base表并回显id
//        authMapper.register(userBase);
//        Long userId = userBase.getId();
//        //获得userId后再写入userINfo
//        userInfo.setUserId(userId);
//        userMapper.insert(userInfo);
//    }

}
