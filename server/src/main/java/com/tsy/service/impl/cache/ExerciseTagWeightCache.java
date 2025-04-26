package com.tsy.service.impl.cache;

import com.tsy.entity.ExerciseTagWeight;
import com.tsy.mapper.ExerciseTagWeightMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExerciseTagWeightCache {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ExerciseTagWeightMapper weightMapper;

    // 用于从Redis拿到全部动作的权重表
    public Map<String, Map<String, Double>> getAll() {
        Map<String, Map<String, Double>> result = new HashMap<>();

        // 假设你有所有训练动作列表（可以自己存一份）
        List<String> exerciseNames = weightMapper.selectAllExerciseNames();

        for (String exerciseName : exerciseNames) {
//            拼接成 Redis 的键，比如 exercise:tag:weight:跑步机
            String redisKey = "exercise:tag:weight:" + exerciseName;
//            拿到这个动作对应的标签-权重的哈希表，比如 {心肺功能=0.8, 脂肪燃烧=0.2}
            Map<Object, Object> tagWeightRaw = redisTemplate.opsForHash().entries(redisKey);
//            转成 Map<String, Double> 格式，放进 result
            Map<String, Double> tagWeight = new HashMap<>();
            for (Map.Entry<Object, Object> entry : tagWeightRaw.entrySet()) {
                tagWeight.put(entry.getKey().toString(), Double.valueOf(entry.getValue().toString()));
            }
            result.put(exerciseName, tagWeight);
        }

        return result;
    }

    // 初始化方法（项目启动时把MySQL数据写入Redis）
//    @PostConstruct
    public void cacheWeightsToRedis() {
        List<ExerciseTagWeight> allWeights = weightMapper.selectAll();
        for (ExerciseTagWeight w : allWeights) {
            String key = "exercise:tag:weight:" + w.getProjectName();
            Map<String, String> tagMap = Map.of(
                    "肌肉增长", String.valueOf(w.getMuscle()),
                    "塑形", String.valueOf(w.getShape()),
                    "核心力量", String.valueOf(w.getCore()),
                    "基础代谢提升", String.valueOf(w.getMetabolism()),
                    "脂肪燃烧", String.valueOf(w.getFatBurn()),
                    "心肺功能", String.valueOf(w.getCardio()),
                    "柔韧性", String.valueOf(w.getFlexibility())
            );
            redisTemplate.opsForHash().putAll(key, tagMap);
        }
        System.out.println("✅ Redis 权重缓存初始化完成");
    }
}
