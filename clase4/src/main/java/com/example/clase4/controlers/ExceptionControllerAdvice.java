package com.example.clase4.controlers;

import com.example.clase4.dtos.ErrorMessage;
import com.example.clase4.exceptions.PasswordNotValidException;
import com.example.clase4.exceptions.UrlNotExistException;
import com.example.clase4.exceptions.UrlNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionControllerAdvice {

    @ExceptionHandler(UrlNotExistException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorMessage handlerException(UrlNotExistException e) {
        return new ErrorMessage(e.getMessage(), HttpStatus.NO_CONTENT.value());
    }
    @ExceptionHandler(UrlNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handlerException(UrlNotValidException e) {
        return new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(PasswordNotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessage handlerException(PasswordNotValidException e) {
        return new ErrorMessage(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
    }

}
