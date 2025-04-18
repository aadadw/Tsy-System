package com.tsy.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class WebMvcConfiguration {
    /**
     * 通过knife4j生成接口文档
     * @return
     */
//    @Bean
//    public Docket docket() {
//        ApiInfo apiInfo = new ApiInfoBuilder()
//                .title("毕设项目接口文档")
//                .version("2.0")
//                .description("毕设项目项目接口文档")
//                .build();
//        Docket docket = new Docket(DocumentationType.OAS_30)
//                .groupName("测试接口")
//                .apiInfo(apiInfo)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.tsy.controller.test"))
//                .paths(PathSelectors.any())
//                .build();
//        return docket;
//    }
    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("毕设项目接口文档")
                .version("2.0")
                .description("毕设项目接口文档")
                .build();

        return new Docket(DocumentationType.OAS_30)
                .groupName("测试接口")
                .apiInfo(apiInfo)
                .select()
                .apis(requestHandler -> {
                    Class<?> controllerClass = requestHandler.declaringClass();
                    return controllerClass.getPackage().getName().startsWith("com.tsy.controller.test");
                })
                .paths(PathSelectors.any())
                .build();
    }

}
