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
@Size(min = 2, max = 32, message = "name must be between 2 and 32 characters long")
@NotBlank(message = "name is mandatory")
@Pattern(regexp = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\s]+\\S$", message = "name can contain only letters")
@Constraint(validatedBy = { })
public @interface ValidUserName {
    String message() default "{jakarta.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
