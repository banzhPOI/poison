package org.poison.starter.web.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

@RestControllerAdvice(annotations = RestController.class)
public class RequestResponseBodyAdvice implements RequestBodyAdvice, ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(final MethodParameter mp, final Type targetType, final Class<? extends HttpMessageConverter<?>> hmc) {
        return true;
    }

    @Override
    public Object handleEmptyBody(final Object body, final HttpInputMessage inputMessage, final MethodParameter parameter, final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage im, final MethodParameter parameter, final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {
        return im;
    }

    @Override
    public Object afterBodyRead(final Object body, final HttpInputMessage inputMessage, final MethodParameter parameter, final Type targetType,
                                final Class<? extends HttpMessageConverter<?>> converterType) {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        requestAttributes.setAttribute("REQUEST_BODY", body, RequestAttributes.SCOPE_REQUEST);
        return body;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        final HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        httpServletRequest.setAttribute("RESPONSE_BODY", body);
        return body;
    }
}
