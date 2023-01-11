package com.example.survey.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@NotBlank(message = "role name is mandatory")
@Size(min = 3, max = 32, message = "role name must be between 3 and 32 characters long")
@Pattern(regexp = "^[a-z_]$", message = "role name can contain only lowercase letters and _ sign")
@Constraint(validatedBy = { })
public @interface ValidRoleName {
    String message() default "{jakarta.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
