package com.tsy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EnrollmentMapper {
    /**
     * 通过课程id查询当前课程报名人数
     * @return
     */
    @Select("select  count(*) from course_enrollment where course_id =#{courseId}")
    public Integer countEnrollment(Long courseId);
}
