package com.example.survey.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@NotBlank(message = "login is mandatory")
@Size(min = 3, max = 32, message = "login must be between 3 and 32 characters long")
@Pattern(regexp = "^\\w+$", message = "login can contain only letters, numbers or _ character")
public @interface ValidLogin {
}
