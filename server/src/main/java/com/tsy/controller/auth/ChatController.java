package com.tsy.controller.auth;

import com.alibaba.fastjson.JSONObject;
import com.tsy.dto.ChatRequestDTO;
import com.tsy.properties.DeepSeekPropertise;
import com.tsy.result.Result;
import com.tsy.utils.HttpClientUtil;
import com.tsy.vo.ChatVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "deepseek聊天相关接口")
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private DeepSeekPropertise deepSeekPropertise;

    @ApiOperation("聊天功能接口")
    @PostMapping
    public Result<ChatVO> chat(@RequestBody ChatRequestDTO chatRequestDTO) throws IOException{
        log.info("测试一个配置类有没注入成功deepseekpropertise:{}",deepSeekPropertise);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "deepseek-chat");
        requestMap.put("messages", chatRequestDTO.getMessages());

        // 构造 Headers 放在 JSON 字符串里（你可以改写工具类支持 headers，但这里我们使用默认方案）
        String response = HttpClientUtil.doPost4JsonWithHeader(deepSeekPropertise.getApiUrl(), requestMap, deepSeekPropertise.getApiKey());

        // 从 response 提取 answer（可选）
        JSONObject obj = JSONObject.parseObject(response);
        log.error("DeepSeek 返回原始结果：{}", response);
        String reply = obj.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
        ChatVO chatVO = new ChatVO();
        chatVO.setContent(reply);
        return Result.success(chatVO);
    }
}
