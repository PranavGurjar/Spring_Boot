package com.validator.electronic.store.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid, String> {

    private Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("message from isValid : {}",value);

        //logic
        if (value.isBlank()){
            System.out.println("Image Name is not valid!!");
            return false;
        }
        else {
            System.out.println("Image Name is valid!!");
            return true;
        }


    }
}
