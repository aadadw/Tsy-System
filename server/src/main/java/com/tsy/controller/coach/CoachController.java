package com.tsy.controller.coach;

import com.github.pagehelper.PageInfo;
import com.tsy.context.BaseContext;
import com.tsy.dto.CoachProfileUpdateDTO;
import com.tsy.dto.CourseAddDTO;
import com.tsy.result.PageResult;
import com.tsy.result.Result;
import com.tsy.service.CoachService;
import com.tsy.service.CourseService;
import com.tsy.vo.CoachProfileVO;
import com.tsy.vo.CourseVO;
import com.tsy.vo.ReservationVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "教练相关接口")
@Slf4j
@RestController
@RequestMapping("/api/coach/")
public class CoachController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CoachService coachService;
    // 新增课程

    /**
     * 教练新建课程
     * @param dto
     * @return
     */
    @PostMapping("/course/add")
    public Result<CourseVO> addCourse(@RequestBody CourseAddDTO dto) {
        log.info("新增课程：{}",dto);
        Long coachId = BaseContext.getCurrentId();
        log.info("coachId:{}",coachId);
        CourseVO vo = courseService.addCourse(dto, coachId);
        return Result.success(vo);
    }

    // 查询我的课程列表
    @GetMapping("/course/list")
    public Result<PageInfo<CourseVO>> listMyCourses(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String tag) {

        Long coachId = BaseContext.getCurrentId();
        PageInfo<CourseVO> pageInfo = courseService.listByCoachIdAndConditions(coachId, name, tag, page, pageSize);
        return Result.success(pageInfo);
    }
    // 获取教练个人资料
    @GetMapping("/profile")
    public Result<CoachProfileVO> getProfile() {
        Long userId = BaseContext.getCurrentId(); // 从登录态获取
        CoachProfileVO vo = coachService.getProfile(userId);
        return Result.success(vo);
    }
    // 更新教练个人资料
    @PostMapping("/profile/update")
    public Result updateProfile(@RequestBody CoachProfileUpdateDTO dto) {
        Long userId = BaseContext.getCurrentId(); // 从登录态获取
        dto.setUserId(userId);
        coachService.updateProfile(dto);
        return Result.success();
    }

    /**
     * 查看学员报名情况
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/reservations")
    public Result<PageResult> listReservations(
            @RequestParam int page,
            @RequestParam int pageSize) {

        PageResult  result = courseService.listReservations(page, pageSize);
        return Result.success(result);
    }

    /**
     * 确认预约
     * @param reservationId 预约ID
     * @return 操作结果
     */
    @PostMapping("/reservations/{reservationId}/confirm")
    public Result confirmReservation(@PathVariable Long reservationId) {
        courseService.confirmReservation(reservationId);
        return Result.success();
    }

    /**
     * 取消预约
     * @param reservationId 预约ID
     * @return 操作结果
     */
    @PostMapping("/reservations/{reservationId}/cancel")
    public Result cancelReservation(@PathVariable Long reservationId) {
        courseService.cancelReservation(reservationId);
        return Result.success();
    }





}
