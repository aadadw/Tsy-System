package com.tsy.service;

import com.tsy.dto.*;
import com.tsy.result.PageResult;
import com.tsy.vo.ProjectProportionVO;
import com.tsy.vo.TrendDataVO;
import com.tsy.vo.UserGoalVO;
import com.tsy.vo.UserProfileVO;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 设置用户阶段目标
     * @param userGoalDTO
     * @return
     */
     UserGoalVO setGoal(UserGoalDTO userGoalDTO) ;


    /**
     * 根据userid删除普通用户数据
     * @param userId
     */
     void logicDeleteById(Long userId) ;

    /**
     * 分页查询用户数据
     * @param userPageQueryDTO
     * @return
     */
    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    void updateStatus(UserStatusDTO dto);

    /**
     * 签到，并返回消耗卡路里
     * @param dto
     * @return
     */
    Integer recordPunch(UserPunchDTO dto);

    /**
     * 检查今日是否签到
     * @return
     */
    Boolean hasCheckedInToday();

    /**
     * 获取用户目标概览（用于首页显示）
     * @return
     */
    UserGoalVO getGoalSummary();

    /**
     * 获得7/30的卡路里消耗
     * @param range
     * @return
     */
    Map<String, Object> getCalorieTrend(String range);

    /**
     * 获取7/30日的打卡趋势
     * @param range
     * @return
     */
    TrendDataVO getTrainingTrend(String range);

    /**
     * 近7/30日健身时长和最近健身日期
     * @param userId
     * @param range
     * @return
     */
    Map<String, Object> getTrainingSummary(Long userId, String range);

    /**
     * 训练项目占比
     * @param userId
     * @return
     */
    List<ProjectProportionVO> getTrainingProportion(Long userId);

    /**
     * 用户个人资料数据回显
     * @return
     */
    UserProfileVO getUserProfile();

    /**
     * 修改个人信息
     * @param userProfileUpdateDTO
     */
    void updateProfile(UserProfileUpdateDTO userProfileUpdateDTO);

//    /**
//     * 普通用户注册
//     * @param userRegisterDTO
//     */
//    void register(UserRegisterDTO userRegisterDTO);
}
