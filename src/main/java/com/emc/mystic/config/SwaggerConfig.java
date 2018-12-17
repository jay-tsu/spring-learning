package com.emc.mystic.config;

import com.emc.mystic.util.webutil.RequestParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket job_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Job")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.emc.mystic.rest.controller"))
                .paths(PathSelectors.ant("/v*/jobs/**"))
                .build()
                .ignoredParameterTypes(RequestParameters.class)
                .securitySchemes(newArrayList(basicAuth()))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                .code(403)
                                .message("Forbidden!")
                                .build()));
    }

    @Bean
    public Docket cluster_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Cluster")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.emc.mystic.rest.controller"))
                .paths(PathSelectors.ant("/v*/cluster/**"))
                .build()
                .ignoredParameterTypes(RequestParameters.class)
                .securitySchemes(newArrayList(basicAuth()))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                .code(403)
                                .message("Forbidden!!!!")
                                .build()));
    }

    @Bean
    public Docket system_api() {
        return api("System", "/v*/system/**");
    }

    static private Docket api(final String groupName, final String path) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.emc.mystic.rest.controller"))
                .paths(PathSelectors.ant(path))
                .build()
                .ignoredParameterTypes(RequestParameters.class)
                .securitySchemes(newArrayList(basicAuth()))
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                .code(403)
                                .message("Forbidden!!!!")
                                .build()));
    }

    static private ApiInfo apiInfo() {
        return new ApiInfo(
                "VxRail REST API",
                "Some custom description of API.",
                "API v1.0",
                "Terms of service",
                ApiInfo.DEFAULT_CONTACT,
                "License of API", "API license URL", Collections.emptyList());
    }

    static private BasicAuth basicAuth() {
        return new BasicAuth("basic");
    }
}
