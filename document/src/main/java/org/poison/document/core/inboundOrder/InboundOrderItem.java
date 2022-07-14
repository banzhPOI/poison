package org.poison.document.core.inboundOrder;

import lombok.Data;
import org.poison.document.core.base.BaseItem;

@Data
public class InboundOrderItem extends BaseItem<InboundOrderItemStatus> {

    /**
     * 单项状态
     */
    InboundOrderItemStatus itemStatus;
}
