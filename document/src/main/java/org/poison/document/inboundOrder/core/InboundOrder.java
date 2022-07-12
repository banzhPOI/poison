package org.poison.document.inboundOrder.core;

import lombok.Data;

import java.util.List;

@Data
public class InboundOrder {

    Long id;

    /**
     * 单号
     */
    String no;

    String userId;

    /**
     * 单据状态
     */
    InboundOrderStatus status;

    /**
     * 单项列表
     */
    List<InboundOrderItem> itemList;
}