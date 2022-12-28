package org.poison.flow.controller;

import lombok.extern.slf4j.Slf4j;
import org.poison.flow.client.FlowClient;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FlowController implements FlowClient {


    @Override
    public void test() {

    }
}
