package com.tsy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tsy.dto.UserPageQueryDTO;
import com.tsy.entity.UserBase;
import com.tsy.entity.UserInfo;
import com.tsy.mapper.AuthMapper;
import com.tsy.mapper.UserMapper;
import com.tsy.result.PageResult;
import com.tsy.service.UserService;
import com.tsy.vo.UserFullVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
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
}
