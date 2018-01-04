package com.emc.mystic;

import com.emc.mystic.util.webutil.RequestParametersResolver;
import com.fasterxml.jackson.databind.Module;
//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestParametersResolver());
    }

//    @Bean
//    public Module hibernateModule() {
//        return new Hibernate5Module();
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
