package com.example.survey.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Size(min = 3, max = 256, message = "address must be at least 3 characters long")
@NotBlank(message = "address is mandatory")
@Pattern(regexp = "^\\S.*\\S$", message = "address can't start or end with whitespace")
@Constraint(validatedBy = { })
public @interface ValidAddress {
    String message() default "{jakarta.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
