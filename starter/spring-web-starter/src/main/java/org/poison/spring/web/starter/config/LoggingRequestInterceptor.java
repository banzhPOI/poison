package org.poison.spring.web.starter.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class LoggingRequestInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String requestURI = request.getRequestURI();
        final long beginTime = System.currentTimeMillis();
        request.setAttribute("beginTime", beginTime);
        final Map<String, String> requestMap = this.extractRequestMap(request);
        log.info("PreHandle Request - [REQUEST URI:{}] [HTTP METHOD:{}] [REQUEST PARAMETERS:{}] ", requestURI, request.getMethod(), requestMap);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        final long beginTime = (Long) request.getAttribute("beginTime");
        final long timeConsume = System.currentTimeMillis() - beginTime;
        final String requestURI = request.getRequestURI();
        final String requestBody = extractBody(request, "REQUEST_BODY");
        final String responseBody = extractBody(request, "RESPONSE_BODY");
        log.info("AfterCompletion RESPONSE - [REQUEST URI:{}] [HTTP METHOD:{}] [TIME CONSUME:{}] [REQUEST BODY:{}] [RETURN:{}]", requestURI, request.getMethod(), timeConsume, requestBody, responseBody);
    }

    /**
     * 解析Request的Parameters,隐藏请求中的username password参数.
     */
    private Map<String, String> extractRequestMap(final HttpServletRequest request) {
        final Map<String, String> requestMap = new HashMap<>();
        final Enumeration<?> requestParamNames = request.getParameterNames();
        while (requestParamNames.hasMoreElements()) {
            final String requestParamName = (String) requestParamNames.nextElement();
            if (!StringUtils.containsAny(requestParamName, "username", "password")) {
                final String requestParamValue = request.getParameter(requestParamName);
                requestMap.put(requestParamName, requestParamValue);
            }
        }
        return requestMap;
    }

    @SneakyThrows
    private String extractBody(final HttpServletRequest request, final String key) {
        if (request.getAttribute(key) != null) {
            final String body = objectMapper.writeValueAsString(request.getAttribute(key));
            request.removeAttribute(key); //属性读完之后删除
            if (StringUtils.isEmpty(body)) {
                return "-";
            } else if (body.length() > 5000) {
                return body.substring(0, 5000);
            } else {
                return body;
            }
        }
        return "-";
    }


}
