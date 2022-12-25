package org.poison.starter.cloud;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * 组合一些springCloud的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@EnableFeignClients(basePackages = "org.poison.**.client")
public @interface EnablePoisonCloud {

}
