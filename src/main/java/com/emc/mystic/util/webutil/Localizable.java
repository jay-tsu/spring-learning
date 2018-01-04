package com.emc.mystic.util.webutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Localizable {
    String bundle() default "";
    String prefix() default "";
    String keyprop() default "";
    String param() default "";
}
