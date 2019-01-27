package com.simple.shop.core.validation.annotation;

import com.simple.shop.core.validation.LengthValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(Length.List.class)
@Documented
@Constraint(validatedBy = {LengthValidator.class})
public @interface Length {

    String message() default "should be between {min} and {max} characters";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Length[] value();
    }
}
