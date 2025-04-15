package com.tsy.propertise;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "deepseek.key")
public class DeepSeekPropertise {
    /**
     *请求deepseek的配置
     */
    private String apiKey;
    private String apiUrl;
}
