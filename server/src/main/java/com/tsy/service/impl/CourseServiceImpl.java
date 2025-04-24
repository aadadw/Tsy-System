package com.tsy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsy.dto.CourseAddDTO;
import com.tsy.entity.Course;
import com.tsy.mapper.CourseMapper;
import com.tsy.service.CourseService;
import com.tsy.vo.CourseRecommendVO;
import com.tsy.vo.CourseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    /**
     * 获取用户推荐课程
     * @return
     */
    @Override
    public List<CourseRecommendVO> getRecommend() {
        /**先要理清一下逻辑
        1.查询用户历史以来打卡项目名称和对应的时间
        2.用项目名称查询redis获得其于tag的相关权值
         3.用权值，项目进行时间占比计算用户偏好
        4.查询数据库所有课程对象，用他的tag和用户偏好进行比对，选出（几个？）课程，返回
         **/
        return null;
    }

    /**
     * 添加课程
     * @param dto
     * @param coachId
     * @return
     */
    @Override
    public CourseVO addCourse(CourseAddDTO dto, Long coachId) {
        Course course = new Course();
        BeanUtils.copyProperties(dto,course);
        course.setCoachId(coachId);
        log.info("测试：属性copy，course：{}",course);
        courseMapper.insert(course);
        return null;
    }

    /**
     * 分页查询我的课程
     * @param coachId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<CourseVO> listByCoachIdAndConditions(Long coachId, String name, String tag, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CourseVO> list = courseMapper.selectByCoachIdAndConditions(coachId, name, tag);
        return new PageInfo<>(list);
    }

}
