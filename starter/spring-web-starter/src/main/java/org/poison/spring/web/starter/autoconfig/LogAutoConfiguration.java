package org.poison.spring.web.starter.autoconfig;

import org.poison.spring.web.starter.config.LoggingRequestInterceptor;
import org.poison.spring.web.starter.config.RequestResponseBodyAdvice;
import org.springframework.context.annotation.Bean;

public class LogAutoConfiguration {

    @Bean
    LoggingRequestInterceptor loggingRequestInterceptor() {
        return new LoggingRequestInterceptor();
    }

    @Bean
    RequestResponseBodyAdvice requestResponseBodyAdvice() {
        return new RequestResponseBodyAdvice();
    }

}
