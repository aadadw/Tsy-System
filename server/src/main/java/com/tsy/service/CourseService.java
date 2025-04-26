package com.tsy.service;

import com.github.pagehelper.PageInfo;
import com.tsy.dto.CourseAddDTO;
import com.tsy.dto.CourseQueryDTO;
import com.tsy.dto.PageQueryDTO;
import com.tsy.result.PageResult;
import com.tsy.vo.CourseRecommendVO;
import com.tsy.vo.CourseVO;

import java.util.List;

public interface CourseService {
    /**
     * 获取用户推荐课程
     * @return
     */
    List<CourseRecommendVO> getRecommend();

    /**
     * 添加课程
     * @param dto
     * @param coachId
     * @return
     */
    CourseVO addCourse(CourseAddDTO dto, Long coachId);

    /**
     * 分页查询我的课程
     * @param coachId
     * @param page
     * @param pageSize
     * @return
     */
    PageInfo<CourseVO> listByCoachIdAndConditions(Long coachId, String name, String tag, int page, int pageSize);

    /**
     * 条件分页查询所用课程
     * @return
     */
    PageResult getCourse(CourseQueryDTO queryDTO);

    /**
     * 报名
     * @param courseId
     */
    void enroll(Long courseId);


    /**
     *  查看用户课程报名情况
     * @param page
     * @param pageSize
     * @return
     */
    PageResult getEnrolledCourses(int page,int pageSize);

    /**
     * 查看学员报名情况
     * @param page
     * @param pageSize
     * @return
     */
    PageResult listReservations(int page, int pageSize);

    /**
     * 确认报名
     * @param reservationId
     */
    void confirmReservation(Long reservationId);

    /**
     * 拒绝报名
     * @param reservationId
     */
    void cancelReservation(Long reservationId);
}
