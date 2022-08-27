package com.joonseolee.musinsasearchassignment.config;

import com.joonseolee.musinsasearchassignment.exception.MusinsaException;
import com.joonseolee.musinsasearchassignment.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {


    @ExceptionHandler(MusinsaException.class)
    public BaseResponse<Object> musinsaException(MusinsaException musinsaException) {
        printErrorLog(musinsaException);
        return new BaseResponse<>(musinsaException);
    }

    @ExceptionHandler({RuntimeException.class})
    public BaseResponse<Object> commonException(RuntimeException runtimeException) {
        printErrorLog(runtimeException);
        return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void printErrorLog(RuntimeException e) {
        log.error(String.format("broke out an error - [%s]", e.getMessage()), e);
    }
}
