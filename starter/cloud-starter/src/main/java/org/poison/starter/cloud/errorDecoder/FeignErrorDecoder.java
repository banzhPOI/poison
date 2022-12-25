package org.poison.starter.cloud.errorDecoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.exception.SysException;

@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    ObjectMapper objectMapperWithType = new ObjectMapper()
            .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    @Override
    public Exception decode(String methodKey, Response response) {

        try {
            String body = Util.toString(response.body().asReader());
            //规范是将Exception序列化之后放在ResponseBean.data里
            return objectMapperWithType.readValue(body, Exception.class);
        } catch (Exception e) {

            return new SysException(methodKey);
        }
    }
}
