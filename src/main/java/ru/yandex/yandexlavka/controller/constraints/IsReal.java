package ru.yandex.yandexlavka.controller.constraints;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = IsRealValidator.class)
public @interface IsReal {
    String message() default "{ru.yandex.yandexlavka.controller.constraints.IsReal.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
