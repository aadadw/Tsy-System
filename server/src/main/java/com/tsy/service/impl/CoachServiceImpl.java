package com.tsy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsy.dto.CoachPageQueryDTO;
import com.tsy.dto.UserStatusDTO;
import com.tsy.mapper.CoachMapper;
import com.tsy.mapper.UserMapper;
import com.tsy.result.PageResult;
import com.tsy.service.CoachService;
import com.tsy.vo.CoachVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    private CoachMapper coachMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 模糊查询coach
     * @param dto
     * @return
     */
    @Override
    public PageResult pageQueryCoach(CoachPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<CoachVO> list = coachMapper.pageQueryCoach(dto);
        PageInfo<CoachVO> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 修改状态
     * @param dto
     */
    @Override
    public void updateStatus(UserStatusDTO dto) {
        userMapper.updateStatus(dto.getId(), dto.getStatus());
    }

    @Override
    public void deleteCoachLogic(Long id) {
        coachMapper.markUserAsDeleted(id);
    }


}
