package org.poison.document.template.core.ctx;

public enum Event {

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
