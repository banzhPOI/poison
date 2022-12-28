package org.poison.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.template.client.TemplateClient;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TemplateController implements TemplateClient {


    @Override
    public void test() {

    }
}
