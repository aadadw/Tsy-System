package com.tsy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsy.context.BaseContext;
import com.tsy.dto.CourseAddDTO;
import com.tsy.dto.CourseQueryDTO;
import com.tsy.dto.PageQueryDTO;
import com.tsy.entity.Course;
import com.tsy.mapper.CourseEnrollMapper;
import com.tsy.mapper.CourseMapper;
import com.tsy.result.PageResult;
import com.tsy.service.CourseService;
import com.tsy.vo.CourseRecommendVO;
import com.tsy.vo.CourseVO;
import com.tsy.vo.ReservationVO;
import com.tsy.vo.UserCourseVO;
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
    @Autowired
    private CourseEnrollMapper courseEnrollMapper;
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

    /**、
     * 用户获取课程数据
     * @param queryDTO
     * @return
     */
    @Override
    public PageResult getCourse(CourseQueryDTO queryDTO) {
        queryDTO.setUserId(BaseContext.getCurrentId());
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());

        List<CourseVO> courseList = courseMapper.selectUserCourses(queryDTO);
        PageInfo<CourseVO> pageInfo = new PageInfo<>(courseList);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 报名课程
     * @param courseId
     */
    @Override
    public void enroll(Long courseId) {
        Long userId = BaseContext.getCurrentId();
        courseEnrollMapper.insert(courseId,userId);
    }

    /**
     * 查看用户课程报名情况
     * @param
     * @return
     */
    @Override
    public PageResult getEnrolledCourses(int page,int pageSize) {
        Long userId = BaseContext.getCurrentId();
        PageHelper.startPage(page,pageSize);
        log.info("我要查询userId的课程报名情况：{}",userId);
        List<UserCourseVO> userCourseVOList = courseEnrollMapper.selectUserEnrolledCourses(userId);
        log.info("课程报名结果：{}",userCourseVOList);
        PageInfo<UserCourseVO> pageInfo = new PageInfo<>(userCourseVOList);
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 查看学员报名情况
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult listReservations(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ReservationVO> voList = courseEnrollMapper.selectReservations();
        PageInfo<ReservationVO> pageInfo = new PageInfo<>(voList);
        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 确认报名
     * @param reservationId
     */
    @Override
    public void confirmReservation(Long reservationId) {
        courseEnrollMapper.updateReservationStatus(reservationId, "已完成");
    }

    /**
     * 拒绝报名
     * @param reservationId
     */
    @Override
    public void cancelReservation(Long reservationId) {
        courseEnrollMapper.updateReservationStatus(reservationId, "取消");
    }

}
