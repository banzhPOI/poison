package org.poison.starter.web.autoconfig;

import org.poison.starter.web.config.LoggingRequestInterceptor;
import org.poison.starter.web.config.RequestResponseBodyAdvice;
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
