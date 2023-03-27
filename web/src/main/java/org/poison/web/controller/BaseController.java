package org.poison.web.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.poison.common.constant.GlobalConstant;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    protected String getCurrentUserId(){
        return request.getHeader(GlobalConstant.CURRENT_USER);
    }
}
