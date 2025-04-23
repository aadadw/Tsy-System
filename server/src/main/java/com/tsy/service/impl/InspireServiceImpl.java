package com.tsy.service.impl;

import com.tsy.service.InspireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class InspireServiceImpl implements InspireService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public String getRandomInspire() {
        Set<String> keys = stringRedisTemplate.keys("inspire:*");
        if (keys == null || keys.isEmpty()) {
            return "暂无鼓励语，请先添加。";
        }

        List<String> keyList = new ArrayList<>(keys);
        String randomKey = keyList.get(new Random().nextInt(keyList.size()));
        return stringRedisTemplate.opsForValue().get(randomKey);
    }
    //插入，一次性
//    @PostConstruct
//    public void initInspireData() {
//        stringRedisTemplate.opsForValue().set("inspire:1", "坚持下去，流的每一滴汗都算数！");
//        stringRedisTemplate.opsForValue().set("inspire:2", "今天的汗水，是明天的成绩！");
//        stringRedisTemplate.opsForValue().set("inspire:3", "你正在变强，每一分努力都不会白费。");
//        stringRedisTemplate.opsForValue().set("inspire:4", "别放弃，你比想象中更有力量！");
//        stringRedisTemplate.opsForValue().set("inspire:5", "再坚持一下，就赢了！");
//        stringRedisTemplate.opsForValue().set("inspire:6", "你流的每滴汗，都是对目标的承诺。");
//        stringRedisTemplate.opsForValue().set("inspire:7", "强者不是没有眼泪，而是含着泪水依然奔跑！");
//        stringRedisTemplate.opsForValue().set("inspire:8", "目标不会辜负每一位努力的人！");
//        stringRedisTemplate.opsForValue().set("inspire:9", "别低头，王冠会掉！");
//        stringRedisTemplate.opsForValue().set("inspire:10", "热爱可抵岁月漫长，坚持终会闪闪发光。");
//        stringRedisTemplate.opsForValue().set("inspire:11", "每一次训练，都是走向更好的自己！");
//        stringRedisTemplate.opsForValue().set("inspire:12", "你不运动，别人替你强大！");
//        stringRedisTemplate.opsForValue().set("inspire:13", "肌肉不会背叛你，偷懒才会！");
//        stringRedisTemplate.opsForValue().set("inspire:14", "运动不止是改变身体，也是磨练意志！");
//        stringRedisTemplate.opsForValue().set("inspire:15", "你跑的每一步，都是对自己的肯定！");
//        stringRedisTemplate.opsForValue().set("inspire:16", "别停下来，你的汗水正在发光！");
//        stringRedisTemplate.opsForValue().set("inspire:17", "没有一滴汗是白流的！");
//        stringRedisTemplate.opsForValue().set("inspire:18", "未来的你，一定会感谢今天坚持的你！");
//        stringRedisTemplate.opsForValue().set("inspire:19", "今天练或不练，夏天穿T恤时就知道了！");
//        stringRedisTemplate.opsForValue().set("inspire:20", "最慢的进步也是进步，坚持才是关键！");
//    }
//    @PostConstruct
//    public void initFitnessTips() {
//        stringRedisTemplate.opsForValue().set("tip:1", "训练前热身可以有效减少受伤风险。");
//        stringRedisTemplate.opsForValue().set("tip:2", "运动后拉伸有助于肌肉放松和恢复。");
//        stringRedisTemplate.opsForValue().set("tip:3", "合理饮食是健身成果的关键部分。");
//        stringRedisTemplate.opsForValue().set("tip:4", "保持充足睡眠有助于肌肉生长。");
//        stringRedisTemplate.opsForValue().set("tip:5", "训练不在多，关键是坚持和科学。");
//        stringRedisTemplate.opsForValue().set("tip:6", "适当的休息是提高训练效果的重要因素。");
//        stringRedisTemplate.opsForValue().set("tip:7", "每天走路一万步对健康有益。");
//        stringRedisTemplate.opsForValue().set("tip:8", "训练前摄入适量碳水可提升表现。");
//        stringRedisTemplate.opsForValue().set("tip:9", "多喝水，保持身体水分平衡。");
//        stringRedisTemplate.opsForValue().set("tip:10", "运动时注意呼吸节奏，保持稳定。");
//
//        stringRedisTemplate.opsForValue().set("tip:11", "力量训练后补充蛋白质有助于恢复。");
//        stringRedisTemplate.opsForValue().set("tip:12", "训练要量力而行，循序渐进。");
//        stringRedisTemplate.opsForValue().set("tip:13", "有氧+力量结合效果更好。");
//        stringRedisTemplate.opsForValue().set("tip:14", "保持训练记录，能帮助你更好地进步。");
//        stringRedisTemplate.opsForValue().set("tip:15", "训练时保持专注，减少分心。");
//        stringRedisTemplate.opsForValue().set("tip:16", "适当补钙和维生素D有助于骨骼健康。");
//        stringRedisTemplate.opsForValue().set("tip:17", "清晨锻炼可提神醒脑，开启活力一天。");
//        stringRedisTemplate.opsForValue().set("tip:18", "选择适合自己的运动项目更容易坚持。");
//        stringRedisTemplate.opsForValue().set("tip:19", "训练中如感到强烈不适要及时停止。");
//        stringRedisTemplate.opsForValue().set("tip:20", "锻炼前后都不要空腹。");
//
//        stringRedisTemplate.opsForValue().set("tip:21", "跑步时注意落地方式，减少膝盖冲击。");
//        stringRedisTemplate.opsForValue().set("tip:22", "高强度间歇训练能有效燃脂。");
//        stringRedisTemplate.opsForValue().set("tip:23", "选择合脚的运动鞋能避免受伤。");
//        stringRedisTemplate.opsForValue().set("tip:24", "训练结束后适当走动可帮助代谢乳酸。");
//        stringRedisTemplate.opsForValue().set("tip:25", "锻炼不只是为了外表，更是为了健康。");
//        stringRedisTemplate.opsForValue().set("tip:26", "坚持比完美更重要。");
//        stringRedisTemplate.opsForValue().set("tip:27", "不要在疲劳状态下进行高强度训练。");
//        stringRedisTemplate.opsForValue().set("tip:28", "训练时保持良好的姿势能提高效率。");
//        stringRedisTemplate.opsForValue().set("tip:29", "小幅度的进步也是值得庆祝的成就。");
//        stringRedisTemplate.opsForValue().set("tip:30", "每天一个小目标，坚持成就大改变！");
//    }
}
