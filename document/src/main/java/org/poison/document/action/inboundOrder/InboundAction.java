package org.poison.document.action.inboundOrder;

import org.poison.document.action.base.BaseAction;
import org.poison.document.core.inboundOrder.InboundEvent;
import org.poison.document.core.inboundOrder.InboundOrderItemStatus;

public interface InboundAction extends BaseAction<InboundOrderItemStatus, InboundEvent> {

}
