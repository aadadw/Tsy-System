package com.tsy.service;

import com.tsy.dto.CoachPageQueryDTO;
import com.tsy.dto.CoachQualificationQueryDTO;
import com.tsy.dto.CoachVerifyDTO;
import com.tsy.dto.UserStatusDTO;
import com.tsy.result.PageResult;

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
}
