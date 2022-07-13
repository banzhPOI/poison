package org.poison.document.template.core;

/**
 * 基础单项状态
 */
public enum DocumentItemStatus {

    /**
     * 初始化创建
     */
    CREATE,

    /**
     * 待执行
     */
    PENDING,

    /**
     * 已完成
     */
    COMPLETE,

    /**
     * 已作废
     */
    INVALID;
}
