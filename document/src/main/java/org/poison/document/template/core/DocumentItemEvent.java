package org.poison.document.template.core;

public enum DocumentItemEvent {

    /**
     * 编辑
     */
    EDIT,

    /**
     * 初始化
     */
    INIT,

    /**
     * 回滚
     */
    ROLL_BACK,

    /**
     * 执行
     */
    EXECUTE,

    /**
     * 取消
     */
    CANCEL;
}
