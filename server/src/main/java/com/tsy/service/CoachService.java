package com.tsy.service;

import com.tsy.dto.*;
import com.tsy.result.PageResult;
import com.tsy.vo.CoachProfileVO;

public interface CoachService {
    /**
     * 模糊查询coach
     * @param dto
     * @return
     */
    PageResult pageQueryCoach(CoachPageQueryDTO dto);

    /**
     * 修改状态
     * @param dto
     */
    void updateStatus(UserStatusDTO dto);

    /**
     * 删除教练(逻辑删除：status=2
     * @param id
     */
    void deleteCoachLogic(Long id);

    /**
     * 分页查询教练资质审核
     * @param dto
     * @return
     */
    PageResult pageQueryForReview(CoachQualificationQueryDTO dto);

    /**
     * 认证教练资质
     * @param dto
     */
    void updateVerificationStatus(CoachVerifyDTO dto);

    /**
     * 通过/驳回教练资质认证
     * @param dto
     */
    void verifyCoach(CoachVerifyDTO dto);

    /**
     * 获取教练个人资料
     * @param userId
     * @return
     */
    CoachProfileVO getProfile(Long userId);

    /**
     * 更新个人资料
     * @param dto
     */
    void updateProfile(CoachProfileUpdateDTO dto);
}
