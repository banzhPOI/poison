package org.poison.template.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "template-service", contextId = "template")
public interface TemplateClient {

    @PostMapping("template/test")
    void test();
}
