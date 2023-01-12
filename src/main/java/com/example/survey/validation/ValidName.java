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
@Size(min = 3, max = 32, message = "category name must be between 3 and 32 characters long")
@NotBlank(message = "name is mandatory")
@Pattern(regexp = "^\\S.*\\S$", message = "name can't start or end with whitespace")
@Constraint(validatedBy = { })
public @interface ValidName {
    String message() default "{jakarta.validation.constraints.Pattern.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
