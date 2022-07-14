package org.poison.document.action.base;

import lombok.Data;
import org.poison.document.core.base.Operator;

@Data
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
