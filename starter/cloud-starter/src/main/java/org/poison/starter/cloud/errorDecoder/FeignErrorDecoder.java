package org.poison.starter.cloud.errorDecoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.BaseException;

import java.nio.charset.StandardCharsets;

@Slf4j
public class FeignErrorDecoder extends ErrorDecoder.Default {


    ObjectMapper objectMapperWithType = new ObjectMapper()
            .activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    @Override
    public Exception decode(String methodKey, Response response) {

        try {
            String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            return objectMapperWithType.readValue(body, BaseException.class);
        } catch (Exception e) {
            // 如果这里抛异常则说明没有走feign的controllerAdvice,需要对异常继续处理,用默认的处理方案,并且属于系统错误
            return super.decode(methodKey, response);
        }
    }
}
