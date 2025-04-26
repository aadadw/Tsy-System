package com.tsy.controller.user;

import com.tsy.context.BaseContext;
import com.tsy.dto.*;
import com.tsy.entity.Course;
import com.tsy.mapper.CoachMapper;
import com.tsy.result.PageResult;
import com.tsy.result.Result;
import com.tsy.service.CourseRecommenderService;
import com.tsy.service.CourseService;
import com.tsy.service.InspireService;
import com.tsy.service.UserService;
import com.tsy.service.impl.cache.ExerciseTagWeightCache;
import com.tsy.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 普通用户管理
 */
@Api(tags = "普通用户相关接口")
@Slf4j
@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private InspireService inspireService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRecommenderService courseRecommenderService;
    @Autowired
    private ExerciseTagWeightCache exerciseTagWeightCache; // 之前写好的缓存
    /**
     * 用户分页查询
     * @param userPageQueryDTO
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("用户分页查询")
    public Result<PageResult> queryPage(UserPageQueryDTO userPageQueryDTO){
        log.info("用户分页查询，参数：{}",userPageQueryDTO);
        PageResult pageResult = userService.pageQuery(userPageQueryDTO);
        return  Result.success(pageResult);
    }

    /**
     * 签到，并返回消耗卡路里
     * @param dto
     * @return
     */
    @PostMapping("/punch")
    public Result<Integer> punch(@RequestBody UserPunchDTO dto) {
        log.info("用户训练签到，用户数据:{}",dto);
        int totalCalories = userService.recordPunch(dto);
        return Result.success(totalCalories);
    }
    /**
     * 检查今日是否签到
     */
    @GetMapping("/checkin/today")
    public Result<Boolean> putnch(){
        log.info("查询当日打卡");
        Boolean checkedIn  = userService.hasCheckedInToday();
        return Result.success(checkedIn);
    }

    /**
     * 设置用户阶段目标
     * @param userGoalDTO
     * @return
     */
    @PostMapping("/set/goal")
    public Result<UserGoalVO> setGoal(@RequestBody UserGoalDTO userGoalDTO){
        log.info("setGoal:{}",userGoalDTO);
        UserGoalVO userGoalVO = userService.setGoal(userGoalDTO);
        return Result.success(userGoalVO);
    }
    /**
     * 获取用户目标概览（用于首页显示）
     */
    @GetMapping("/goal-summary")
    public Result<UserGoalVO> getGoalSummary() {
        UserGoalVO goalVO = userService.getGoalSummary();
        goalVO.setInspire(inspireService.getRandomInspire());
        log.info("测试：用户目标概览（用于首页显示）{}",goalVO);
        return Result.success(goalVO);
    }

    /**
     * 从 Redis 返回一条健身小建议
     */
    @GetMapping("/fitnesstip")
    public Result getFitnesstip() {
        Set<String> keys = stringRedisTemplate.keys("tip:*");
        if (keys == null || keys.isEmpty()) {
            return Result.success("暂无健身建议，请先添加！");
        }
        List<String> keyList = new ArrayList<>(keys);
        String randomKey = keyList.get(new Random().nextInt(keyList.size()));
        String tip = stringRedisTemplate.opsForValue().get(randomKey);
        return Result.success(tip); // 直接返回一条建议
    }

    /**
     * 获取7/30天的卡路里消耗
     * @param range
     * @return
     */
    @GetMapping("/training/calories")
    public Result<Map<String, Object>> getCalorieTrend(@RequestParam(defaultValue = "7") String range) {
        Map<String, Object> data = userService.getCalorieTrend(range);
        return Result.success(data);
    }

    /**
     * 获得7/30的打卡数据
     * @param range
     * @return
     */
    @GetMapping("/training/trend")
    public Result<TrendDataVO> getTrainingTrend(@RequestParam String range) {
        Long userId = BaseContext.getCurrentId(); // 拿当前登录用户ID
        TrendDataVO data = userService.getTrainingTrend(range);
        return Result.success(data);
    }

    /**
     * 近7/30日健身时长和最近健身日期
     * @param range
     * @return
     */
    @GetMapping("/training/summary")
    public Result<Map<String, Object>> getTrainingSummary(@RequestParam(defaultValue = "7") String range) {
        Long userId = BaseContext.getCurrentId();
        Map<String, Object> result = userService.getTrainingSummary(userId, range);
        return Result.success(result);
    }

    /**
     * 训练项目占比
     * @return
     */
    @GetMapping("/training/pie")
    public Result<List<ProjectProportionVO>> getTrainingProportion() {
        log.info("查询训练项目占比数据...");
        Long userId = BaseContext.getCurrentId();
        List<ProjectProportionVO> list = userService.getTrainingProportion(userId);
        return Result.success(list);
    }

    /**
     * 用户个人资料数据回显
     * @return
     */
    @GetMapping("/profile")
    public Result<UserProfileVO> getUserProfile(){
        UserProfileVO userProfileVO = userService.getUserProfile();
        return Result.success(userProfileVO);
    }

    /**
     * 修改个人信息
     * @param userProfileUpdateDTO
     * @return
     */
    @PostMapping("/profile/update")
    public Result updateProfile(@RequestBody UserProfileUpdateDTO userProfileUpdateDTO){
        userService.updateProfile(userProfileUpdateDTO);
        return Result.success();
    }

    /**
     * 用户界面显示课程
     * @return
     */
    @GetMapping("/course/list")
    public Result<PageResult> listCourses(CourseQueryDTO queryDTO) {
        PageResult p = courseService.getCourse(queryDTO);

        return Result.success(p);
    }

    /**
     * 报名课程
     * @param courseId
     * @return
     */
    @PostMapping("/course/enroll")
    public Result enroll(@RequestParam  Long courseId) {
        courseService.enroll(courseId);
        return Result.success();
    }

    /**
     * 用户查看课程报名情况
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/course/enrolled")
    public Result<PageResult> listEnrolledCourses(        @RequestParam int page,
                                                          @RequestParam int pageSize ) {

//        coach_info
        PageResult pageResult = courseService.getEnrolledCourses(page,pageSize);
        return Result.success(pageResult);
    }

    /**
     * 获取推荐课程Top5
     * @return
     */
    @GetMapping("/course/recommend")
    public Result<List<Course>> getRecommendCourses(){
        Long userId = BaseContext.getCurrentId();
        Map<String, Double> userExerciseProportions = courseRecommenderService.getUserExerciseProportions(userId);
        List<Course> list = courseRecommenderService.recommendTopCourses(userId,userExerciseProportions);
        return Result.success(list);
    }

    /**
     * 获取用户七维画像
     * @return
     */
    @GetMapping("/trainProfile")
    public Result<Map<String, Double>> getUserTrainProfile() {
        Long userId = BaseContext.getCurrentId();
        Map<String, Double> userExerciseProportions = courseRecommenderService.getUserExerciseProportions(userId);

        Map<String, Map<String, Double>> exerciseTagWeights = exerciseTagWeightCache.getAll();
        Map<String, Double> userProfile = courseRecommenderService.buildUserProfile(userExerciseProportions, exerciseTagWeights);
        return Result.success(userProfile);
    }
}
