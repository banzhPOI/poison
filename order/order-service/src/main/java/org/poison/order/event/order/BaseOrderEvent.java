package org.poison.order.event.order;

import jakarta.annotation.Resource;
import org.poison.order.core.enums.BaseStatus;
import org.poison.order.dao.OrderDao;
import org.poison.order.event.BaseEvent;
import org.poison.order.pojo.dto.OrderDTO;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseOrderEvent extends BaseEvent {

    @Resource
    private OrderDao orderDao;

    public static final String DOC_PREFIX = "ORDER";

    /**
     * 需要重写这个方法
     * 单据前缀（加锁用）
     */
    @Override
    public String getDocPrefix() {
        return DOC_PREFIX;
    }

    /**
     * 需要重写这个方法
     * 根据id获取单据
     *
     * @param docId
     */
    @Override
    protected OrderDTO getDocById(String docId) {
        return orderDao.findById(docId);
    }

    /**
     * 需要重写这个方法
     * 更新单据目标状态
     *
     * @param docId
     * @param status
     */
    @Override
    protected void updateStatus(String docId, BaseStatus status) {
        orderDao.updateStatusById(docId, status);
    }
}