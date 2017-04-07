package utilities.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlwaysTrueValidator implements ConstraintValidator<AlwaysTrue, Boolean> {

	@Override
	public void initialize(AlwaysTrue constraintAnnotation) {

	}

	@Override
	public boolean isValid(Boolean b, ConstraintValidatorContext cvc) {

		return b==true;
	}
}
