package com.tsy.controller.coach;

import com.github.pagehelper.PageInfo;
import com.tsy.context.BaseContext;
import com.tsy.dto.CoachProfileUpdateDTO;
import com.tsy.dto.CourseAddDTO;
import com.tsy.result.Result;
import com.tsy.service.CoachService;
import com.tsy.service.CourseService;
import com.tsy.vo.CoachProfileVO;
import com.tsy.vo.CourseVO;
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





}
