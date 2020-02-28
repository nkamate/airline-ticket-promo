package org.example.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = TravelDateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TravelDate {
    String message() default "Travel date should be after the booking date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}