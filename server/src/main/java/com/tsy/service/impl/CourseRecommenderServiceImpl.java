package com.tsy.service.impl;

import com.tsy.entity.Course;
import com.tsy.mapper.CourseMapper;
import com.tsy.mapper.TrainingLogMapper;
import com.tsy.model.CourseWithScore;
import com.tsy.service.CourseRecommenderService;
import com.tsy.service.impl.cache.ExerciseTagWeightCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CourseRecommenderServiceImpl implements CourseRecommenderService {
    @Autowired
    private ExerciseTagWeightCache exerciseTagWeightCache; // 之前写好的缓存
    @Autowired
    private CourseMapper courseMapper; // 查询所有课程
    @Autowired
    private TrainingLogMapper trainingLogMapper;
    @Override
    public List<Course> recommendTopCourses(Long userId, Map<String, Double> userExerciseProportions) {
        // 1. 从 Redis 拿动作→标签权重表
        Map<String, Map<String, Double>> exerciseTagWeights = exerciseTagWeightCache.getAll();

        // 2. 生成用户画像
        Map<String, Double> userProfile = buildUserProfile(userExerciseProportions, exerciseTagWeights);
        double[] userVector = buildUserVector(userProfile);

        // 3. 查询所有课程
        List<Course> courses = courseMapper.selectAllCourses();

        // 4. 计算每个课程与用户的相似度
        List<CourseWithScore> scoredCourses = new ArrayList<>();
        for (Course course : courses) {
            double[] courseVector = buildCourseVector(course.getTags());
            double similarity = cosineSimilarity(userVector, courseVector);
            scoredCourses.add(new CourseWithScore(course, similarity));
        }

        // 5. 按得分排序，取Top N
        return scoredCourses.stream()
                .sorted((a, b) -> Double.compare(b.getScore(), a.getScore()))//按照得分排序（从高到低）
                .limit(5) // 推荐Top 5
                .map(CourseWithScore::getCourse)//取出CourseWithScore里面的Course
                .collect(Collectors.toList());//把上面处理好的 5 个 Course 收集成一个 List<Course> 返回。

    }

    /**
     * 计算用户七维向量（把一个"动作 → (标签 → 权重)" 的二层嵌套Map，融合成一个 "标签 → 综合得分" 的单层Map。
     * @param userExerciseProportions
     * @param exerciseTagWeights
     * @return
     */
    //感觉这个方法可以单独做一个echart？
    @Override
    public Map<String, Double> buildUserProfile(Map<String, Double> userExerciseProportions, Map<String, Map<String, Double>> exerciseTagWeights) {
        Map<String, Double> profile = new HashMap<>();
        // 初始化7个标签（创建一个空的画像，包含7个标签，每个初始得分设为0。
        String[] tags = {"肌肉增长", "塑形", "核心力量", "基础代谢提升", "脂肪燃烧", "心肺功能", "柔韧性"};
        for (String tag : tags) {
            profile.put(tag, 0.0);
        }
        //entrySet() 方法是 Map 自带的，它把每一组 key-value打包成一个 Entry 对象。
        //返回一个集合(Set)，里面每一项是一个 Map.Entry<String, Double>
        for (Map.Entry<String, Double> entry : userExerciseProportions.entrySet()) {
            String exercise = entry.getKey();// 拿到动作名字（比如 "跑步机"）
            Double proportion = entry.getValue(); // 比如跑步机占比0.2
            Map<String, Double> tagWeight = exerciseTagWeights.get(exercise);
            if (tagWeight != null) {
                for (Map.Entry<String, Double> tagEntry : tagWeight.entrySet()) {
                    String tag = tagEntry.getKey();
                    Double weight = tagEntry.getValue();
                    profile.put(tag, profile.get(tag) + proportion * weight);
                }
            }
        }
        log.info("用户画像七维向量为：{}",profile);
        return profile;
    }

    /**
     * 课程标签向量生成（把一个课程的主标签（比如“肌肉增长”）转换成一个7维的 one-hot 向量。
     * @param courseTag
     * @return
     */
    @Override
    public double[] buildCourseVector(String courseTag) {
        //后面构造向量时，要严格按照这个顺序来放 1 和 0。
        String[] tags = {"肌肉增长", "塑形", "核心力量", "基础代谢提升", "脂肪燃烧", "心肺功能", "柔韧性"};
        double[] vector = new double[tags.length];
        for (int i = 0; i < tags.length; i++) {
            vector[i] = tags[i].equals(courseTag) ? 1.0 : 0.0;
        }
        return vector;
    }

    /**
     * 把用户的7维标签画像（Map形式）转换成7维数组（double[]）
     * @param userProfile
     * @return
     */
    @Override
    public double[] buildUserVector(Map<String, Double> userProfile) {
        String[] tags = {"肌肉增长", "塑形", "核心力量", "基础代谢提升", "脂肪燃烧", "心肺功能", "柔韧性"};
        double[] vector = new double[tags.length];
        for (int i = 0; i < tags.length; i++) {
            vector[i] = userProfile.getOrDefault(tags[i], 0.0);
        }
        log.info("用户标签画像七维向量项关值\"肌肉增长\", \"塑形\", \"核心力量\", \"基础代谢提升\", \"脂肪燃烧\", \"心肺功能\", \"柔韧性\"：{}",vector);
        return vector;
    }

    /**
     * 计算余弦相似度
     * @param a
     * @param b
     * @return
     */
    @Override
    public double cosineSimilarity(double[] a, double[] b) {
        //初始化点积（分子）和两个向量的模长平方（分母部分）。
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < a.length; i++) {
            dotProduct += a[i] * b[i];//点积A*B
            normA += a[i] * a[i];// A向量的模长平方累加
            normB += b[i] * b[i]; // B向量的模长平方累加
        }

        if (normA == 0 || normB == 0) return 0.0;
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    /**
     * 获得用户<项目-时间占比>map。负责给buildUserProfile方法传值
     * @param userId
     * @return
     */
    public Map<String, Double> getUserExerciseProportions(Long userId) {
        List<Map<String, Object>> projectDurations = trainingLogMapper.selectProjectDurations(userId);

        Integer totalDuration = trainingLogMapper.selectTotalDuration(userId);

        Map<String, Double> userExerciseProportions = new HashMap<>();
        if (totalDuration != null && totalDuration > 0) {
            for (Map<String, Object> record : projectDurations) {
                String projectName = (String) record.get("project_name");
                BigDecimal durationDecimal = (BigDecimal) record.get("total_duration");
                double duration = durationDecimal.doubleValue();
                double proportion = duration / totalDuration;
                userExerciseProportions.put(projectName, proportion);
            }
        }
        return userExerciseProportions;
    }

}
