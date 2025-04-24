package com.tsy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsy.dto.*;
import com.tsy.entity.CoachInfo;
import com.tsy.entity.UserBase;
import com.tsy.mapper.CoachMapper;
import com.tsy.mapper.UserMapper;
import com.tsy.result.PageResult;
import com.tsy.service.CoachService;
import com.tsy.vo.CoachProfileVO;
import com.tsy.vo.CoachQualificationVO;
import com.tsy.vo.CoachVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
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

    /**
     * 逻辑删除教练
     * @param id
     */
    @Override
    public void deleteCoachLogic(Long id) {
        coachMapper.markUserAsDeleted(id);
    }

    /**
     * 分页查询教练资质审核
     * @param dto
     * @return
     */
    @Override
    public PageResult pageQueryForReview(CoachQualificationQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<CoachQualificationVO> list = coachMapper.pageQueryForReview(dto);
        PageInfo<CoachQualificationVO> pageInfo = new PageInfo<>(list);
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 认证教练资质
     * @param dto
     */
    @Override
    public void updateVerificationStatus(CoachVerifyDTO dto) {
        coachMapper.updateVerificationStatus(dto.getUserId(), dto.getIsVerified());
    }

    /**
     * 通过/驳回教练资质认证
     * @param dto
     */
    @Override
    public void verifyCoach(CoachVerifyDTO dto) {
        coachMapper.updateVerifyStatus(dto);
    }

    /**
     * 获取教练个人资料
     * @param userId
     * @return
     */
    @Override
    public CoachProfileVO getProfile(Long userId) {
        /*下面两个mapper的select其实可以外键链接一次性查完的，也省去了copy的逻辑*/
        CoachInfo coach = coachMapper.selectById(userId);
        UserBase user = userMapper.selectById(userId);
        CoachProfileVO vo = new CoachProfileVO();
        BeanUtils.copyProperties(user,vo);
        BeanUtils.copyProperties(coach,vo);
        /*上面两次拷贝感觉会出事，可能漏数据或是覆盖为空？*/
        log.info("测试两次拷贝结果：{}",vo);
        return vo;
    }

    /**
     * 更新个人资料
     * @param dto
     */
    @Override
    public void updateProfile(CoachProfileUpdateDTO dto) {
        coachMapper.updateProfile(dto);
    }


}
