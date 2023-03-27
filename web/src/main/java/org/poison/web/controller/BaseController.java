package org.poison.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.poison.account.core.resp.UserVO;
import org.poison.common.constant.GlobalConstant;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private ObjectMapper objectMapper;

    protected UserVO getCurrentUser() {
        try {
            return objectMapper.readValue(request.getHeader(GlobalConstant.CURRENT_USER), UserVO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
