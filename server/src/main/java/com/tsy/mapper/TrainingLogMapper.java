package com.tsy.mapper;

import com.tsy.dto.TrendPointDTO;
import com.tsy.dto.UserGoalStatsDTO;
import com.tsy.entity.UserTrainingLog;
import com.tsy.vo.DailyCalorieVO;
import com.tsy.vo.ProjectProportionVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface TrainingLogMapper {
    /**
     * 写入数据用户训练记录
     * @param log
     */
    void insert(UserTrainingLog log);

//    /**
//     * 通过userId获取用户训练打卡日志
//     * @param userId
//     * @return
//     */
//    UserTrainingLog getByUserId(Long userId);

    /**
     *获取 GoalStatsDTO
     * @param userId
     * @param startDate
     * @return
     */
    UserGoalStatsDTO selectGoalStats(Long userId, LocalDate startDate);

    /**
     * 获取期间打卡天数
     * @param userId
     * @param startDate
     * @return
     */
    LocalDate getLastCheckinDate(Long userId, LocalDate startDate);

    /**
     * 获取本周打卡天数
     * @param userId
     * @param startDate
     * @return
     */
    Integer countThisWeekCheckinDays(Long userId, LocalDate startDate);

    /**
     * 查询startDate-endDate之间的消耗
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    List<DailyCalorieVO> queryDailyCalories(Long userId, LocalDate startDate, LocalDate endDate);

    /**
     * 同统计时段内打卡天数
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    List<TrendPointDTO> countCheckinsPerDay(Long userId, LocalDate startDate, LocalDate endDate);



    /**
     * 返回时段内所有UserTrainingLog数据
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    List<UserTrainingLog> queryLogsBetween(Long userId, LocalDate startDate, LocalDate endDate);

    /**
     * 查询训练项目名称和时间
     * @param userId
     * @return
     */
    List<ProjectProportionVO> queryProjectProportion(Long userId);

    /**
     * 获得用户<项目-时间>map
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectProjectDurations(@Param("userId") Long userId);

    /**
     * 获取用户课程总时长
     * @param userId
     * @return
     */
    Integer selectTotalDuration(Long userId);
}
