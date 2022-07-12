package org.poison.document.template.core;

import lombok.Data;

import java.util.List;

/**
 * 基础单据类，包含基础单据信息
 */
@Data
public class TemplateDocument {

    Long id;

    /**
     * 单号
     */
    String no;

    String userId;

    /**
     * 单据状态
     */
    TemplateDocumentStatus status;

    /**
     * 单项列表
     */
    List<TemplateDocumentItem> itemList;
}
