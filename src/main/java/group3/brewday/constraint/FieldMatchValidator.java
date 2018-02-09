package group3.brewday.constraint;

import org.apache.commons.beanutils.BeanUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldPsw;
    private String secondFieldPsw;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldPsw = constraintAnnotation.first();
        secondFieldPsw = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldPsw);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldPsw);
            return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        } catch (final Exception ignore) {}
        return true;
    }
}