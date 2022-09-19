package com.dong.beta.annotation;

import com.dong.beta.config.jackson.JacksonConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(JacksonConfig.class)
@Documented
public @interface EnableClassJsonConfig {
}