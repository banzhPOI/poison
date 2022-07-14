package org.poison.document.core.base;

public abstract class BaseOperate {

    String userId;


    /*========== ext ==========*/

    /**
     * 单据id
     */
    String documentId;

    /**
     * 备注
     */
    String remark;

    /**
     * 操作人信息
     */
    Operator operator;
}
