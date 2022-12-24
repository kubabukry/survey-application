package com.example.survey.validation;

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
public @interface ValidAddress {
}
