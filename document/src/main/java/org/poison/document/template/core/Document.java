package org.poison.document.template.core;

import lombok.Data;

import java.util.List;

/**
 * 基础单据类，包含基础单据信息
 */
@Data
public class Document {

    Long id;

    /**
     * 单号
     */
    String no;

    String userId;

    /**
     * 单据状态
     */
    DocumentStatus status;

    /**
     * 单项列表
     */
    List<DocumentItem> itemList;
}
