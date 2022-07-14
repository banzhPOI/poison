package org.poison.document.inboundOrder.core;

/**
 * 入库单单项状态
 */
public enum InboundOrderItemStatus {

    /**
     * 初始化创建
     */
    CREATE,

    /**
     * 待执行
     */
    PENDING,

    /**
     * 部分执行
     */
    PART_EXECUTE,

    /**
     * 已完成
     */
    COMPLETE,

    /**
     * 已作废
     */
    INVALID
}
