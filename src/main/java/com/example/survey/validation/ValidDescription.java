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
@Size(min = 12, max = 1024, message = "category description must be at least 12 characters long")
@NotBlank(message = "description is mandatory")
@Pattern(regexp = "^\\S.*\\S$", message = "description can't start or end with whitespace")
@Constraint(validatedBy = { })
public @interface ValidDescription {
    String message() default "{jakarta.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
