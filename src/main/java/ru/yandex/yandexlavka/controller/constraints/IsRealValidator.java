package ru.yandex.yandexlavka.controller.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class IsRealValidator implements ConstraintValidator<IsReal, String> {

    @Override
    public void initialize(IsReal constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            new BigDecimal(value);
            return true;
        } catch (Throwable e){
            return false;
        }
    }
}
