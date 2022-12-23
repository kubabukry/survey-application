package com.example.survey.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Size(min = 2, max = 32, message = "name must be between 2 and 32 characters long")
@NotBlank(message = "name is mandatory")
@Pattern(regexp = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+$", message = "name can contain only letters")
public @interface ValidUserName {
}
