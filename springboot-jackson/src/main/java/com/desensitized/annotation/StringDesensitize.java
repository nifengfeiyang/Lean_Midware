package com.desensitized.annotation;

import com.desensitized.desensitization.StringDesensitization;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Desensitize(desensitization = StringDesensitization.class)
@Documented
public @interface StringDesensitize {
}
