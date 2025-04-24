package com.tsy.service;

import com.github.pagehelper.PageInfo;
import com.tsy.dto.CourseAddDTO;
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
}
