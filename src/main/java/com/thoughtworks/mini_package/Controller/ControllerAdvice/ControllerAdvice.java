package com.thoughtworks.mini_package.Controller.ControllerAdvice;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorReponse notFoundException(NotFoundException e) {
        ErrorReponse errorReponse = new ErrorReponse();
        errorReponse.setMessage(e.getMessage());
        errorReponse.setCode(404);
        return errorReponse;
    }

    @ExceptionHandler(value = InterruptedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorReponse interruptedException(InterruptedException e) {
        ErrorReponse errorReponse = new ErrorReponse();
        errorReponse.setMessage(e.getMessage());
        errorReponse.setCode(400);
        return errorReponse;
    }

}
