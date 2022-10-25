package org.poison.spring.web.starter.autoconfig;

import org.poison.spring.web.starter.config.LoggingRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，这里选择拦截所有请求地址，进入后判断是否有加注解即可
        registry.addInterceptor(new LoggingRequestInterceptor());
    }
}
