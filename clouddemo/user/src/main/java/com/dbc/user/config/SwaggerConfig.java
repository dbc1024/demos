package com.dbc.user.config;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("token").description("用户口令").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        List<Parameter> aParameters = Lists.newArrayList();
        aParameters.add(aParameterBuilder.build());



        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(aParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dbc.user"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户服务")
                .description("用户服务Api接口文档")
                .version("1.0")
                .build();
    }

}
