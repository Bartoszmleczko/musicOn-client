package com.client.musicOn.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FieldMatchValidator.class)
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldMatch {

    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String first();
    String second();

    @Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
     @interface List {

        FieldMatch[] value();

    }

}
