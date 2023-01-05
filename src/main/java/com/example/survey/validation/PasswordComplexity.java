package com.example.survey.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

//custom adnotation do walidacji hasła
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@NotBlank(message = "password is mandatory")
@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
        message = "password must contain: a digit, a lower case letter, an uppercase letter, a special character," +
                " must be at least 8 characters long and cannot contain any spaces")
public @interface PasswordComplexity {
}
