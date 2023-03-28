package org.poison.starter.cloud.controllerAdvice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.poison.common.exception.BaseException;
import org.poison.common.exception.SysException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class FeignControllerAdvice {

    @Value("${spring.application.name}")
    private String appName;

    ObjectMapper objectMapperWithType = new ObjectMapper()
            .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<String> handleBaseException(BaseException e) throws JsonProcessingException {
        HttpStatus httpStatus;
        if (e instanceof SysException) {
            log.error("BaseControllerAdvice handleBaseException with error: " + " app: " + e.getAppName() + " message: " + e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            log.warn("BaseControllerAdvice handleBaseException with info: " + " app: " + e.getAppName() + " message: " + e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (StringUtils.isEmpty(e.getAppName())) {
            e.setAppName(appName);
        }
        return new ResponseEntity<>(objectMapperWithType.writeValueAsString(e), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerException(Exception e) throws JsonProcessingException {
        log.error("BaseControllerAdvice handleException with error:", e);
        // 转换成SysException往上传递
        SysException se = new SysException(e.getMessage());
        se.setAppName(appName);
        return new ResponseEntity<>(objectMapperWithType.writeValueAsString(se), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
