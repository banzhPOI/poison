package org.poison.document.core.base;

import java.util.List;

/**
 * 基础单据状态
 */
public interface BaseDocumentStatus<S, SI> {

    /**
     * 单据状态计算器
     */
    S statusCalculator(List<SI> itemStatusList);
}
