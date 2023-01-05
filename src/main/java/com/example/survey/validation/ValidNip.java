package com.example.survey.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

//10 cyfr w nip

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@NotBlank(message = "nip is mandatory")
//10 cyfr w nip
@Pattern(regexp = "^\\d{10}$", message = "wrong nip format")
public @interface ValidNip {
}
