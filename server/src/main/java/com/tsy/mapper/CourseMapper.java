package com.tsy.mapper;

import com.tsy.annotation.AutoFill;
import com.tsy.dto.CourseAddDTO;
import com.tsy.dto.CourseQueryDTO;
import com.tsy.entity.Course;
import com.tsy.enumeration.OperationType;
import com.tsy.vo.CourseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     *新增课程
     * @param course
     */
    @AutoFill(OperationType.INSERT)
    void insert(Course course);

    /**
     * 查询我的（教练）课程
     * @param coachId
     * @return
     */
    List<CourseVO> selectByCoachIdAndConditions(@Param("coachId") Long coachId,
                                                @Param("name") String name,
                                                @Param("tag") String tag);

    /**
     * 所有课程
     * @param queryDTO
     * @return
     */
    List<CourseVO> selectUserCourses(CourseQueryDTO queryDTO);

    /**
     * 余弦相似度用，返回所有课程
     * @return
     */
    @Select("select * from course")
    List<Course> selectAllCourses();
}
