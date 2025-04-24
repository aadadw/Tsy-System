package com.tsy.controller.course;

import com.tsy.result.Result;
import com.tsy.service.CourseService;
import com.tsy.vo.CourseRecommendVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Api(tags = "课程相关接口")
@Slf4j
@RestController
@RequestMapping("/api/course/")
public class CourseController {
    @Autowired
    private CourseService courseService;
    /**
     * 获取用户推荐课程
     * @return
     */
    @GetMapping("/recommend")
    public Result<List<CourseRecommendVO>> recommendCourses() {
        // 实际开发中请从数据库查询
        List<CourseRecommendVO> list = courseService.getRecommend();
//                Arrays.asList(
//                new CourseRecommendVO(1L, "HIIT 高强度训练", "https://example.com/hiit.jpg"),
//                new CourseRecommendVO(2L, "力量训练基础", "https://example.com/strength.jpg")
//        );
        return Result.success(list);
    }
}
