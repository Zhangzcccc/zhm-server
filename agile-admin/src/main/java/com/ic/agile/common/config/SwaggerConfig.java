package com.ic.agile.common.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.ic.agile.common.constant.Constant;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置
 *
 * @author zhangzc
 */
@Configuration
@EnableSwagger2WebMvc
@AllArgsConstructor
public class SwaggerConfig {
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，生成接口文档
                //.apis(RequestHandlerSelectors.basePackage("com.ic.agile.modules.job.controller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions("Renren"))
                .directModelSubstitute(java.util.Date.class, String.class)
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("玉成合创")
                .description("agile-admin文档")
                .termsOfServiceUrl("https://topai.lzuck.us.kg")
                .version("5.x")
                .build();
    }

    private List<ApiKey> security() {
        ApiKey key = new ApiKey(Constant.TOKEN_HEADER, Constant.TOKEN_HEADER, "header");

        List<ApiKey> list = new ArrayList<>();
        list.add(key);
        return list;
    }
}