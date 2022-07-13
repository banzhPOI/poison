package org.poison.document.template.core;

import org.poison.statemachine.Condition;
import lombok.Data;

/**
 * 基础单项类，包含基础单项信息
 */
@Data
public class DocumentItem {

    Long id;

    /**
     * 单据id
     */
    String documentId;

    String userId;

    /**
     * 单项状态
     */
    DocumentItemStatus itemStatus;

    public static Condition<DocumentItem> checkCondition() {
//        (ctx) -> {
//            ctx.
//        }
        return null;
    }
}
