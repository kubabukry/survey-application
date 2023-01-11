package com.example.survey.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//10 cyfr w nip

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@NotNull(message = "nip is mandatory")
//10 cyfr w nip
@Pattern(regexp = "^\\d{10}$", message = "wrong nip format")
@Constraint(validatedBy = { })
public @interface ValidNip {
    String message() default "{jakarta.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
