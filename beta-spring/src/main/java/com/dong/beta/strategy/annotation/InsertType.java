package com.dong.beta.strategy.annotation;

import com.dong.beta.strategy.enums.InsertTypeEnum;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InsertType {
    InsertTypeEnum value();
}
