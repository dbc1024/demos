package com.ectrip.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("分布式票务电商系统")
                .description("票务电商系统接口文档说明")
                .termsOfServiceUrl("http://localhost:8082")
                .contact(new Contact("vker", "", "**"))
                .version("1.0")
                .build();
    }

	@Bean
    UiConfiguration uiConfig() {
//    	(null, "list", "alpha", "schema",
//    			UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
        return UiConfigurationBuilder.builder().build();
    }
}
