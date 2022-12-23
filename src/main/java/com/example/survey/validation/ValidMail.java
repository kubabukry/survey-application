package com.example.survey.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

//RFC 5322 standard expression ^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "wrong email format")
@NotBlank(message = "mail is mandatory")
public @interface ValidMail {
}
