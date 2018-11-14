package com.dbc.user.annotation;

import java.lang.annotation.*;

/**
 * @author: CSZ
 * @date: 2018/11/14 13:54
 */
@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictionaryCode {

    public String value() default "";
}
