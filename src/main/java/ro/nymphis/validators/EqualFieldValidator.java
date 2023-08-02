package ro.nymphis.validators;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;
import ro.nymphis.validators.annotations.ValidateEqualFields;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class EqualFieldValidator implements ConstraintValidator<ValidateEqualFields, Object> {

    private String firstField;
    private String secondField = "";

    @Override
    public void initialize(ValidateEqualFields constraintAnnotation) {
        firstField = constraintAnnotation.firstField();
        secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {

        if(StringUtils.hasLength(firstField) && StringUtils.hasLength(secondField)) {
            try {
                final Object firstFieldValue = BeanUtils.getProperty(object, firstField);
                final Object secondFieldValue = BeanUtils.getProperty(object, secondField);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

        }

        return true;
    }
}
