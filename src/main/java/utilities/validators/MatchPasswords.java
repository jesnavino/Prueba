//package utilities.validators;
//
//import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
//import static java.lang.annotation.ElementType.TYPE;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import javax.validation.ReportAsSingleViolation;
//
//@Documented
//@Constraint(validatedBy = { MatchPasswordsValidator.class })
//@Target({ TYPE, ANNOTATION_TYPE })
//@Retention(RUNTIME)
//@ReportAsSingleViolation
//public @interface MatchPasswords {
//
//	String message() default "";
//
//	Class<?>[] groups() default {};
//
//	Class<? extends Payload>[] payload() default {};
//}