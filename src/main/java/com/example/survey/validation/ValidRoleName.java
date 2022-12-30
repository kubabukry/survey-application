package com.example.survey.validation;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@NotBlank(message = "role name is mandatory")
@Size(min = 3, max = 32, message = "role name must be between 3 and 32 characters long")
@Pattern(regexp = "^[a-z_]$", message = "role name can contain only lowercase letters and _ sign")
public @interface ValidRoleName {
}
