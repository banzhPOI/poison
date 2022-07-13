package org.poison.document.template.core.ctx;

import lombok.Data;

@Data
public class Context {

    /**
     * 单项id
     */
    Long id;

    String userId;

    String operatorId;

    String operatorName;
}
