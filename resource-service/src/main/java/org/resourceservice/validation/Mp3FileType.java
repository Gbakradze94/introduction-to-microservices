package org.resourceservice.validation;

import jakarta.validation.Constraint;

import javax.ws.rs.ConstrainedTo;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = Mp3Validator.class)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mp3FileType {
    String message() default "Invalid RequestBody data type";
}
