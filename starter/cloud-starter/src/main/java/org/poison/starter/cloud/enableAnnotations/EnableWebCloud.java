package org.poison.starter.cloud.enableAnnotations;

import org.poison.starter.cloud.controllerAdvice.WebControllerAdvice;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 组合一些springCloud的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableFeignClients(basePackages = "org.poison.**.client")
@Import(WebControllerAdvice.class)
public @interface EnableWebCloud {

}
