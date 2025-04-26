package com.tsy.service;

import com.tsy.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseRecommenderService {
    /**
     * 课程TOP推荐
     * @param userId
     * @param userExerciseProportions
     * @return
     */
    List<Course> recommendTopCourses(Long userId, Map<String, Double> userExerciseProportions);

    /**
     * 用户画像生成逻辑（打卡动作 → 标签倾向）
     * @param userExerciseProportions
     * @param exerciseTagWeights
     * @return
     */
    Map<String, Double> buildUserProfile(Map<String, Double> userExerciseProportions, Map<String, Map<String, Double>> exerciseTagWeights);

    /**
     * 课程标签向量生成
     * @param courseTag
     * @return
     */
    double[] buildCourseVector(String courseTag);

    /**
     * 用户画像向量转换
     * @param userProfile
     * @return
     */
    double[] buildUserVector(Map<String, Double> userProfile);

    /**
     * 余弦相似度计算
     * @param a
     * @param b
     * @return
     */
    double cosineSimilarity(double[] a, double[] b);
    /**
     * 获得用户<项目-时间占比>map。负责给buildUserProfile方法传值
     * @param userId
     * @return
     */
    public Map<String, Double> getUserExerciseProportions(Long userId);
}
