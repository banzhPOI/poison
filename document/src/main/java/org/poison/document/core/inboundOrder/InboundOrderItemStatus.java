package org.poison.document.core.inboundOrder;

import org.poison.document.core.base.BaseItemStatus;

/**
 * 入库单单项状态
 */
public enum InboundOrderItemStatus implements BaseItemStatus {

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
