package com.example.survey.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.survey.exception.CategoryAlreadyExistsException;
import com.example.survey.exception.CompanyAlreadyUsedTemplateException;
import com.example.survey.exception.CompanyNameAlreadyInUseException;
import com.example.survey.exception.CompanyNipAlreadyInUseException;
import com.example.survey.exception.ErrorResponse;
import com.example.survey.exception.LoginAlreadyInUseException;
import com.example.survey.exception.MailAlreadyInUseException;
import com.example.survey.exception.NoSuchCategoryExistsException;
import com.example.survey.exception.NoSuchCompanyExistsException;
import com.example.survey.exception.NoSuchCompanySurveyExistsException;
import com.example.survey.exception.NoSuchQuestionExistsException;
import com.example.survey.exception.NoSuchRegisteredUserException;
import com.example.survey.exception.NoSuchRoleExistsException;
import com.example.survey.exception.NoSuchSurveySurveyAnswerExistsException;
import com.example.survey.exception.NoSuchSurveyTemplateExistsException;
import com.example.survey.exception.QuestionAlreadyAnsweredException;
import com.example.survey.exception.QuestionAlreadyExistsException;
import com.example.survey.exception.RoleAlreadyExistsException;
import com.example.survey.exception.RoleIsInUseException;
import com.example.survey.exception.TitleAlreadyExistsException;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(value = NoSuchCompanyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchCompanyExistsException(NoSuchCompanyExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchQuestionExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchQuestionExistsException(NoSuchQuestionExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = QuestionAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleQuestionAlreadyExistsException(QuestionAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchSurveyTemplateExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchSurveyTemplateExistsException(NoSuchSurveyTemplateExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = TitleAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleTitleAlreadyExistsException(TitleAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchCategoryExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchCategoryExistsException(NoSuchCategoryExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = CategoryAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCategoryAlreadyExistsException(CategoryAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = CompanyNameAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCompanyNameAlreadyInUseException(CompanyNameAlreadyInUseException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = CompanyNipAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCompanyNipAlreadyInUseException(CompanyNipAlreadyInUseException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchRegisteredUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchRegisteredUserException(NoSuchRegisteredUserException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = LoginAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleLoginAlreadyInUseException(LoginAlreadyInUseException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = MailAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleMailAlreadyInUseException(MailAlreadyInUseException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchRoleExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchRoleExistsException(NoSuchRoleExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = RoleAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleRoleAlreadyExistsException(RoleAlreadyExistsException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = RoleIsInUseException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public ErrorResponse handleRoleIsInUse(RoleIsInUseException e){
        return new ErrorResponse(HttpStatus.IM_USED.value(), e.getMessage());
    }

    @ExceptionHandler(value = CompanyAlreadyUsedTemplateException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    public ErrorResponse handleCompanyAlreadyUsedTemplateException(CompanyAlreadyUsedTemplateException e){
        return new ErrorResponse(HttpStatus.IM_USED.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchCompanySurveyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchCompanySurveyExistsException(NoSuchCompanySurveyExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(value = QuestionAlreadyAnsweredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleQuestionAlreadyAnsweredException(QuestionAlreadyAnsweredException e){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }

    @ExceptionHandler(value = NoSuchSurveySurveyAnswerExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleNoSuchSurveyAnswerExistsException(NoSuchSurveySurveyAnswerExistsException e){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
