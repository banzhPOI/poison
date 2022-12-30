package org.poison.order.pojo.BasePojo;

import lombok.Data;
import org.poison.order.core.enums.BaseStatus;

import java.time.Instant;

@Data
public class BaseDocEntity {

    /**
     * id
     */
    private String id;

    /**
     * 状态
     */
    private BaseStatus status;

    /**
     * 创建时间
     */
    private Instant createTime;

    /**
     * 更新时间
     */
    private Instant updateTime;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人名
     */
    private String operatorName;

    /**
     * 失败原因
     */
    private String failReason;
}
