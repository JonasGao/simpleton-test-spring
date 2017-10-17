package org.jonas.test.spring.customcomponent;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HsJob {
    @AliasFor(value = "value", annotation = Component.class)
    String value() default "";
}
