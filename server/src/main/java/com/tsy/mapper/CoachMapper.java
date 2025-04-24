package com.tsy.mapper;

import com.tsy.annotation.AutoFill;
import com.tsy.dto.CoachPageQueryDTO;
import com.tsy.dto.CoachProfileUpdateDTO;
import com.tsy.dto.CoachQualificationQueryDTO;
import com.tsy.dto.CoachVerifyDTO;
import com.tsy.entity.CoachInfo;
import com.tsy.enumeration.OperationType;
import com.tsy.vo.CoachQualificationVO;
import com.tsy.vo.CoachVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CoachMapper {
    /**
     * 写入教练数据
     * @param coachInfo
     */
    @AutoFill(OperationType.INSERT)//TODO
    void insert(CoachInfo coachInfo);

    /**
     * 模糊查询教练
     * @param dto
     * @return
     */
    List<CoachVO> pageQueryCoach(CoachPageQueryDTO dto);

    /**
     * 逻辑删除
     */
    void markUserAsDeleted(Long id);

    /**
     * 分页查询教练资质审核
     * @param dto
     * @return
     */
    List<CoachQualificationVO> pageQueryForReview(CoachQualificationQueryDTO dto);

    /**
     * 认证教练资质
     * @param userId
     * @param isVerified
     */
    void updateVerificationStatus(Long userId, Integer isVerified);

    /**
     * 通过/驳回教练资质认证
     * @param dto
     */
    void updateVerifyStatus(CoachVerifyDTO dto);
    @Select("select * from coach_info where user_id = #{userId} ")
    CoachInfo selectById(Long userId);

    /**
     * 更行个人资料
     * @param dto
     */
    @AutoFill(OperationType.UPDATE)
    void updateProfile(CoachProfileUpdateDTO dto);
}
