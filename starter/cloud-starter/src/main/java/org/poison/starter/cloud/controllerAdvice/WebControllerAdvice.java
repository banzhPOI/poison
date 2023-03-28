package org.poison.starter.cloud.controllerAdvice;


import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BaseException;
import org.poison.common.exception.SysException;
import org.poison.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

    // 从下层服务传过来的一定走这里
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Response<?>> handleBaseException(BaseException e) {
        HttpStatus httpStatus;
        String message;
        if (e instanceof SysException) {
            log.error("BaseControllerAdvice handleBaseException with error: " + " app: " + e.getAppName() + " message: " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            message = "系统异常，请稍后再试";
        } else {
            log.warn("BaseControllerAdvice handleBaseException with info: " + " app: " + e.getAppName() + " message: " + e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
            message = e.getMessage();
        }
        return new ResponseEntity<>(Response.fail(message), httpStatus);
    }

    // 这里理论上只有当前服务的异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handlerException(Exception e) {
        log.error("BaseControllerAdvice handleException with error:", e);
        return new ResponseEntity<>(Response.fail("系统异常，请稍后再试"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
