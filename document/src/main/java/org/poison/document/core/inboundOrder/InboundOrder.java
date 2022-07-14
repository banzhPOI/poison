package org.poison.document.core.inboundOrder;

import lombok.Data;
import org.poison.document.core.base.Base;

import java.util.List;

@Data
public class InboundOrder extends Base<InboundOrderStatus, InboundOrderItem> {

    /**
     * 单据状态
     */
    InboundOrderStatus status;

    /**
     * 单项列表
     */
    List<InboundOrderItem> itemList;
}