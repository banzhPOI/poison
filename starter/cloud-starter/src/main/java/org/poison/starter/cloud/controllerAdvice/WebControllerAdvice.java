package org.poison.starter.cloud.controllerAdvice;


import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BaseException;
import org.poison.common.exception.SysException;
import org.poison.common.response.Response;
import org.poison.starter.cloud.enableAnnotations.WebCloud;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
@ConditionalOnBean(WebCloud.class)
public class WebControllerAdvice {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Response<?>> handleBaseException(BaseException e) {
        HttpStatus httpStatus;
        if (e instanceof SysException) {
            log.error("BaseControllerAdvice handleBaseException with error:", e);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            log.warn("BaseControllerAdvice handleBaseException with info: " + e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(Response.fail(e.getMessage()), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handlerException(Exception e) {
        log.error("BaseControllerAdvice handleException with error:", e);
        return new ResponseEntity<>(Response.fail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
