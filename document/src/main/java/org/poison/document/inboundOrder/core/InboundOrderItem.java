package org.poison.document.inboundOrder.core;

import lombok.Data;

@Data
public class InboundOrderItem {

    Long id;

    /**
     * 单据id
     */
    String documentId;

    String userId;

    /**
     * 单项状态
     */
    InboundOrderItemStatus itemStatus;
}
