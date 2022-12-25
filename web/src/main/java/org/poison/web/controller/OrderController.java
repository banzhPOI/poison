package org.poison.web.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.poison.order.client.OrderClient;
import org.poison.order.client.OrderOperateClient;
import org.poison.order.core.req.OrderCreateRequest;
import org.poison.order.core.req.OrderOperateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("orders")
public class OrderController {

    @Resource
    private OrderClient orderClient;

    @Resource
    private OrderOperateClient orderOperateClient;

    @PostMapping(value = "create")
    public void create(@RequestBody OrderCreateRequest createRequest) {
        orderClient.create(createRequest);
    }

    @PostMapping(value = "operate/pass")
    public void create(@RequestBody OrderOperateRequest operateRequest) {
        orderOperateClient.pass(operateRequest);
    }


}
