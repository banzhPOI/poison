package org.poison.document.event.inboundOrder;

import org.poison.document.event.base.BaseEvent;

public interface InboundOrderItemEvent extends BaseEvent {


    /**
     * 部分入库
     * @param userId
     * @param id 入库单项id
     * @param quantity 部分入库数量
     * @param finish 结束入库
     */
    void partExecute(String userId,Long id,Long quantity,Boolean finish);
}
