package ru.yandex.yandexlavka.controller.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.NonNull;
import ru.yandex.yandexlavka.entity.TimeInterval;

public class TimeIntervalValidator implements ConstraintValidator<Interval, TimeInterval> {

    @Override
    public void initialize(Interval constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(@NonNull TimeInterval value, ConstraintValidatorContext context) {
        return value.getFrom().compareTo(value.getTo()) < 0;
    }
}
