package org.poison.order.components;

import jakarta.annotation.Resource;
import org.poison.order.event.order.BaseOrderEvent;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
public class DocLockComponent {

    @Resource
    private RedissonClient redisson;

    public RLock getOrderLock(String id) {
        return redisson.getLock(BaseOrderEvent.DOC_PREFIX + id);
    }
}
