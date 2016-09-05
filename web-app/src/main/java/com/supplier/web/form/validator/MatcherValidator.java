package com.supplier.web.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.security.util.FieldUtils;

import com.supplier.exception.WebAppException;

public class MatcherValidator implements ConstraintValidator<Matcher, Object> {
    private String field1;
    private String field2;

    @Override
    public void initialize(Matcher constraintAnnotation) {
        this.field1 = constraintAnnotation.field1();
        this.field2 = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object form, ConstraintValidatorContext context) {
        try{
            final Object firstObj = FieldUtils.getFieldValue(form, field1);
            final Object secondObj = FieldUtils.getFieldValue(form, field2);
            return firstObj.equals(secondObj);
        }
        catch (Exception e){
            throw new WebAppException("Unable to compare field");
        }
    }

}
