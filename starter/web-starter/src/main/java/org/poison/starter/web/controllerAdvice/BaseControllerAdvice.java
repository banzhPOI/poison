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
    public ResponseEntity<String> handleBaseException(BaseException ex) throws JsonProcessingException {
        HttpStatus httpStatus;
        if (ex instanceof SysException) {
            log.error("BaseControllerAdvice handleBaseException with error:", ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            log.warn("BaseControllerAdvice handleBaseException with info: " + ex.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(objectMapper.writeValueAsString(ex), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception ex) throws JsonProcessingException {
        log.error("BaseControllerAdvice handleException with error:", ex);
        return new ResponseEntity<>(objectMapper.writeValueAsString(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
