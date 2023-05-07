package ru.yandex.yandexlavka.controller.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeIntervalValidator.class)
@Repeatable(Interval.List.class)
public @interface Interval {
    String message() default "{ru.yandex.yandexlavka.controller.constraints.Interval.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ FIELD, PARAMETER})
    @Retention(RUNTIME)
    @interface List {
        Interval[] value();
    }
}
