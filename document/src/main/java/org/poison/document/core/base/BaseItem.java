package org.poison.document.core.base;

import lombok.Data;

/**
 * 基础单项类，包含基础单项信息
 */
@Data
public abstract class BaseItem<S> {

    Long id;

    /**
     * 单据id
     */
    String documentId;

    String userId;

    /**
     * 单项状态
     */
    S itemStatus;
}
