package org.poison.methodidempotent;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodIdempotent {

    /**
     * key的过期时间3s
     */
    int expire() default 3;
}
