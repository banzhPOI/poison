package org.poison.document.inboundOrder.core;

import java.util.List;

/**
 * 基础单据状态
 */
public enum InboundOrderStatus {

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
    public static InboundOrderStatus documentStatusCal(List<InboundOrderItemStatus> itemStatusList) {
        return null;
    }
}
