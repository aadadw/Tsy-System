package com.tsy.controller.coach;

import com.github.pagehelper.PageInfo;
import com.tsy.context.BaseContext;
import com.tsy.dto.CourseAddDTO;
import com.tsy.result.Result;
import com.tsy.service.CourseService;
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


}
