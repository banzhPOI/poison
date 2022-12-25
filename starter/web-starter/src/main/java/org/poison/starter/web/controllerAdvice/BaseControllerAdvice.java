package org.poison.starter.web.controllerAdvice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BaseException;
import org.poison.common.exception.SysException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class BaseControllerAdvice {

    @Resource
    private ObjectMapper objectMapper;

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<String> handleBaseException(BaseException e) throws JsonProcessingException {
        HttpStatus httpStatus;
        if (e instanceof SysException) {
            log.error("BaseControllerAdvice handleBaseException with error:", e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            log.warn("BaseControllerAdvice handleBaseException with info: " + e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(objectMapper.writeValueAsString(e), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception e) throws JsonProcessingException {
        log.error("BaseControllerAdvice handleException with error:", e);
        return new ResponseEntity<>(objectMapper.writeValueAsString(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
