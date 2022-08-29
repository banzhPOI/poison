package org.poison.merge.set;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseTask implements Serializable {

    /**
     * 重试次数
     */
    private Integer retryTimes;

    /**
     * 获取唯一表示，用以去重
     * 示例：
     * public String getUniqueKey() {
     * return id;
     * }
     */
    public abstract String getUniqueKey();


}
