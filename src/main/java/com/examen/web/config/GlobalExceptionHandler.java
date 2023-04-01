package com.examen.web.config;


import com.examen.config.CommonRuntimeException;
import com.examen.config.RestReturn;
import com.examen.config.RestReturnEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<RestReturnEntity<Object>> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return RestReturn.fail(e.getMessage());
    }

    @ExceptionHandler(value = {CommonRuntimeException.class})

    @ResponseBody
    public ResponseEntity<RestReturnEntity<Object>> exceptionHandler(CommonRuntimeException e) {
        log.error("excepción genérica: {}", e.getMessage());
        return RestReturn.fail(e.getMessage());
    }

}
