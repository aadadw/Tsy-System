package com.tsy.mapper;

import com.tsy.annotation.AutoFill;
import com.tsy.vo.ReservationVO;
import com.tsy.vo.UserCourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface CourseEnrollMapper {
    /**
     * 学员报名
     * @param courseId
     * @param userId
     */
    void insert(Long courseId, Long userId);

    /**
     * 用户报名的课程
     * @return
     */
    List<UserCourseVO> selectUserEnrolledCourses(Long userId);

    /**
     * 获取报名情况
     * @return
     */
    List<ReservationVO> selectReservations();


    /**
     * 修改课程报名状态
     * @param id
     * @param status
     */
    void updateReservationStatus(@Param("id") Long id, @Param("status") String status);
}
