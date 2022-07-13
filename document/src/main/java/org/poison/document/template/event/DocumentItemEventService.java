package org.poison.document.template.event;

import org.poison.document.template.core.DocumentItem;

public interface DocumentItemEventService {

    /**
     * 初始化
     */
    DocumentItem init(String userId,Long id);

    /**
     * 回滚
     */
    DocumentItem rollback(String userId,Long id);

    /**
     * 执行
     */
    DocumentItem execute(String userId,Long id);

    /**
     * 取消
     */
    DocumentItem cancel(String userId,Long id);
}
