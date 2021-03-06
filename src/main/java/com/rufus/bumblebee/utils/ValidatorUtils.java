package com.rufus.bumblebee.utils;

import lombok.experimental.UtilityClass;

import javax.validation.*;
import java.util.Set;

@UtilityClass
public class ValidatorUtils {

    public static void validate(Object object) throws ValidationException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        for (ConstraintViolation<Object> cv : constraintViolations) {
            throw new ValidationException("Передано не валидное значение :".concat(String.valueOf(cv.getInvalidValue())));
        }
    }


}
