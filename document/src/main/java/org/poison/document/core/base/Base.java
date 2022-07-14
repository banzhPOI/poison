package org.poison.document.core.base;

import lombok.Data;

import java.util.List;

/**
 * 基础单据类，包含基础单据信息
 */
@Data
public abstract class BaseDocument<S, I> {

    Long id;

    /**
     * 单号
     */
    String no;

    String userId;

    /**
     * 单据状态
     */
    S status;

    /**
     * 单项列表
     */
    List<I> itemList;
}
