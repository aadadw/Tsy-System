//package com.tsy.cache;
//
//import com.tsy.entity.ExerciseTagWeight;
//import com.tsy.mapper.ExerciseTagWeightMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class ExerciseTagWeightCache {
//
//    @Autowired
//    @Qualifier("redisTemplateObject")
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Autowired
//    private ExerciseTagWeightMapper weightMapper;
//
//    /**
//     * 将exercise_tag_weight写入redis
//     */
//    @PostConstruct
//    public void cacheWeightsToRedis() {
//        List<ExerciseTagWeight> allWeights = weightMapper.selectAll();
//        for (ExerciseTagWeight w : allWeights) {
//            String key = "exercise:tag:weight:" + w.getProjectName();
//            Map<String, Object> tagMap = Map.of(
//                    "肌肉增长", w.getMuscle(),
//                    "塑形", w.getShape(),
//                    "核心力量", w.getCore(),
//                    "基础代谢提升", w.getMetabolism(),
//                    "脂肪燃烧", w.getFatBurn(),
//                    "心肺功能", w.getCardio(),
//                    "柔韧性", w.getFlexibility()
//            );
//            redisTemplate.opsForHash().putAll(key, tagMap);
//        }
//        System.out.println("✅ 项目标签权重数据已加载到 Redis");
//    }
//}
