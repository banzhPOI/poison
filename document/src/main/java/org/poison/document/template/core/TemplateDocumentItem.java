package org.poison.document.template.core;

import lombok.Data;

/**
 * 基础单项类，包含基础单项信息
 */
@Data
public class TemplateDocumentItem {

    Long id;

    /**
     * 单据id
     */
    String documentId;

    String userId;

    /**
     * 单项状态
     */
    TemplateDocumentItemStatus itemStatus;
}
