package org.poison.starter.cloud.controllerAdvice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BaseException;
import org.poison.common.exception.SysException;
import org.poison.starter.cloud.enableAnnotations.FeignCloud;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
@ConditionalOnBean(FeignCloud.class)
public class FeignControllerAdvice {

    ObjectMapper objectMapperWithType = new ObjectMapper()
            .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

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
        return new ResponseEntity<>(objectMapperWithType.writeValueAsString(e), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception e) throws JsonProcessingException {
        log.error("BaseControllerAdvice handleException with error:", e);
        return new ResponseEntity<>(objectMapperWithType.writeValueAsString(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
