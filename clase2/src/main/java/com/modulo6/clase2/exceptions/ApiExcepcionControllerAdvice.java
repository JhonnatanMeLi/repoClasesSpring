package com.modulo6.clase2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice(annotations = RestController.class)
public class ApiExcepcionControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handlerException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processField(fieldErrors);
    }

    private ErrorMessage processField(List<FieldError> fieldErrors) {
        HashMap<String, String> fields = new HashMap<String, String>();
        for(FieldError fieldError: fieldErrors) {
            fields.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Validations error", fields);
    }

}
