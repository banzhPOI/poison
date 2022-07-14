package org.poison.document.core.inboundOrder;

import org.poison.document.core.base.BaseStatus;

import java.util.List;

/**
 * 基础单据状态
 */
public enum InboundOrderStatus implements BaseStatus<InboundOrderStatus, InboundOrderItemStatus> {

    /**
     * 初始化创建
     */
    CREATE,

    /**
     * 待执行
     */
    PENDING,

    /**
     * 部分完成
     */
    PART_COMPLETE,

    /**
     * 部分作废
     */
    PART_INVALID,

    /**
     * 已完成
     */
    COMPLETE,

    /**
     * 已作废
     */
    INVALID;

    /**
     * 单据状态计算器
     */
    @Override
    public InboundOrderStatus statusCalculator(List<InboundOrderItemStatus> itemStatusList) {
        return null;
    }
}
