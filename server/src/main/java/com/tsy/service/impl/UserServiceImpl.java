package com.tsy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tsy.context.BaseContext;
import com.tsy.dto.*;
import com.tsy.entity.UserBase;
import com.tsy.entity.UserGoal;
import com.tsy.entity.UserInfo;
import com.tsy.entity.UserTrainingLog;
import com.tsy.exception.BusinessException;
import com.tsy.mapper.*;
import com.tsy.result.PageResult;
import com.tsy.service.UserService;
import com.tsy.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private ExerciseMapper exerciseMapper;
    @Autowired
    private TrainingLogMapper trainingLogMapper;
    @Autowired
    private UserGoalMapper userGoalMapper;
    /**
     * 设置阶段目标
     * @param userGoalDTO
     * @return
     */
    @Override
    public UserGoalVO setGoal(UserGoalDTO userGoalDTO) {
        Long userId = BaseContext.getCurrentId();
        UserGoal userGoal = new UserGoal();
        BeanUtils.copyProperties(userGoalDTO,userGoal);
        //补全数据
        userGoal.setUserId(userId);
        LocalDate date = LocalDate.now();
        userGoal.setStartDate(date);
        //createtime和uptime用标签apo
        userGoalMapper.insert(userGoal);
        return null;
    }

    /**
     * 根据userid删除数据(logic
     * @param id
     */
    @Override

    public void logicDeleteById(Long id) {
        userMapper.markAsDeleted(id);
    }

    /**
     * 用户分页查询
     * @param dto
     * @return
     */
    @Override
    public PageResult pageQuery(UserPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<UserFullVO> voList = userMapper.pageQuery(dto);
        Page<UserFullVO> page = (Page<UserFullVO>) voList;
        return new PageResult(page.getTotal(), page.getResult());
    }


    @Override
    public void updateStatus(UserStatusDTO dto) {
        userMapper.updateStatus(dto.getId(), dto.getStatus());
    }

    /**
     * 签到，并返回消耗卡路里
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Integer recordPunch(UserPunchDTO dto) {
        Long userId = dto.getUserId();
        LocalDate today = LocalDate.now();

        // 查询体重
        Integer weight = userMapper.getWeightByUserId(userId);
        log.info("体重为：{}",weight);
        if (weight == null) {
            throw new BusinessException("用户体重信息缺失");
        }

        int totalCalories = 0;

        for (UserPunchDTO.ExerciseItem item : dto.getExercises()) {
            String projectName = item.getProjectName();
            int minutes = item.getDurationMinutes();

            // 查询 METs
            BigDecimal mets = exerciseMapper.getMetsByName(projectName);
            if (mets == null) {
                throw new BusinessException("找不到项目：" + projectName);
            }

            // 卡路里 = METs * 体重 * 时间(小时)
            BigDecimal hours = BigDecimal.valueOf(minutes).divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
            int calories = mets.multiply(BigDecimal.valueOf(weight)).multiply(hours).setScale(0, RoundingMode.HALF_UP).intValue();

            // 插入记录
            UserTrainingLog logs = new UserTrainingLog();
            logs.setUserId(userId);
            logs.setProjectName(projectName);
            logs.setDurationMinutes(minutes);
            logs.setCalories(calories);
            logs.setCheckinDate(today);
            logs.setCreatedAt(LocalDateTime.now());

            trainingLogMapper.insert(logs);
            totalCalories += calories;
            log.info("计算出来calories：{}",calories);
            log.info("计算出来total：{}",totalCalories);
        }
        log.info("计算出来total：{}",totalCalories);
        return totalCalories;
    }

    /**
     * 检查今日是否签到
     * @return
     */
    @Override
    public Boolean hasCheckedInToday() {
        Long userId = BaseContext.getCurrentId();
        LocalDate today = LocalDate.now();
        int count =  userMapper.countTodayPunch(userId,today);
        log.info("测试：查询count为;{}",count);
        return count>0;
    }

    /**
     * 获取用户目标概览（用于首页显示）
     * @return
     */
    @Override
    public UserGoalVO getGoalSummary() {
        Long userId = BaseContext.getCurrentId();

        // 1. 查询当前目标信息
        UserGoal userGoal = userGoalMapper.getByUserId(userId);
        if (userGoal == null) return null;

        LocalDate startDate = userGoal.getStartDate();
        Integer totalDay = userGoal.getTotalDays();

        // 2. 统计训练数据（合并查询）
        UserGoalStatsDTO userGoalStatsDTO = trainingLogMapper.selectGoalStats(userId, startDate);

        // 3. 查询最近打卡日期
        LocalDate lastCheckin = trainingLogMapper.getLastCheckinDate(userId, startDate);

        // 4. 查询本周打卡天数
        Integer daysThisWeek = trainingLogMapper.countThisWeekCheckinDays(userId, startDate);

        // 5. 封装返回值
        UserGoalVO userGoalVO = new UserGoalVO();
        userGoalVO.setGoalType(userGoal.getGoalType());
        userGoalVO.setGoalDesc(userGoal.getGoalDesc());
        userGoalVO.setCheckinCount(userGoalStatsDTO.getCheckinCount());
        userGoalVO.setTotalDuration(userGoalStatsDTO.getTotalDuration());
        userGoalVO.setProgress((int) ((double) userGoalStatsDTO.getProgressDays() / totalDay * 100));
        userGoalVO.setTotalCalories(userGoalStatsDTO.getTotalCalories());
        userGoalVO.setDaysThisWeek(daysThisWeek);
        userGoalVO.setLastCheckin(lastCheckin);
        userGoalVO.setStartData(startDate);
        // 不设置鼓励语 inspire 字段

        return userGoalVO;
    }
    /**
     * 获得7/30的卡路里消耗
     * @param range
     * @return
     */
    @Override
    public Map<String, Object> getCalorieTrend(String range) {
        Long userId = BaseContext.getCurrentId();
        int days = "30".equals(range) ? 30 : 7;

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days - 1);

        // 查询打卡记录
        List<DailyCalorieVO> records = trainingLogMapper.queryDailyCalories(userId, startDate, endDate);

        // 将记录转为 Map<日期, 卡路里>
        Map<String, Integer> dateToCalorie = records.stream()
                .collect(Collectors.toMap(DailyCalorieVO::getDate, DailyCalorieVO::getCalories));

        List<String> xAxis = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d");

        for (int i = 0; i < days; i++) {
            LocalDate date = startDate.plusDays(i);
            String label = date.format(formatter);
            xAxis.add(label);
            data.add(dateToCalorie.getOrDefault(date.toString(), 0));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("xAxis", xAxis);
        result.put("data", data);
        return result;
    }

    /**
     * 获取7/30日的打卡趋势
     * @param range
     * @return
     */
    @Override
    public TrendDataVO getTrainingTrend(String range) {
        // 解析 range
        int days = "30".equals(range) ? 30 : 7;
        Long userId =BaseContext.getCurrentId();
        // 获取日期范围
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days - 1);

        // 查询打卡记录（每天的打卡次数）
        List<TrendPointDTO> rawList = trainingLogMapper.countCheckinsPerDay(userId, startDate, endDate);

        // 构建日期 → 次数的映射，便于填充缺失日期
        Map<String, Integer> dateToCount = new HashMap<>();
        for (TrendPointDTO point : rawList) {
            dateToCount.put(point.getDate(), point.getCount());
        }

        List<String> xAxis = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < days; i++) {
            LocalDate date = startDate.plusDays(i);
            String key = date.format(DateTimeFormatter.ofPattern("MM-dd"));
            xAxis.add(key);
            data.add(dateToCount.getOrDefault(date.toString(), 0)); // date.toString 是 yyyy-MM-dd
        }

        return TrendDataVO.builder()
                .xAxis(xAxis)
                .data(data)
                .build();
    }

    /**
     * 近7/30日健身时长和最近健身日期
     * @param userId
     * @param range
     * @return
     */
    @Override
    public Map<String, Object> getTrainingSummary(Long userId, String range) {
        int days = "30".equals(range) ? 30 : 7;
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(days - 1);

        List<UserTrainingLog> logs = trainingLogMapper.queryLogsBetween(userId, startDate, endDate);

        int totalMinutes = 0;
        LocalDate latest = null;

        for (UserTrainingLog log : logs) {
            totalMinutes += log.getDurationMinutes();
            if (latest == null || log.getCheckinDate().isAfter(latest)) {
                latest = log.getCheckinDate();
            }
        }

        String lastCheckinTime = latest != null ? latest.toString() : "-";
        Map<String, Object> result = new HashMap<>();
        result.put("totalMinutes", totalMinutes);
        result.put("lastCheckinTime", lastCheckinTime);
        return result;
    }

    /**
     * 训练项目占比
     * @param userId
     * @return
     */
    @Override
    public List<ProjectProportionVO> getTrainingProportion(Long userId) {
        return trainingLogMapper.queryProjectProportion(userId);
    }


}
