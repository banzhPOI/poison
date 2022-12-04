package org.poison.compactqueue;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseTask implements Serializable {

    /**
     * 获取唯一表示，用以去重
     * 示例：
     * <pre class="code">
     * public String getUniqueKey() {
     *      return id;
     * } </pre>
     */
    public abstract String getUniqueKey();

    /**
     * 重试次数(失败重新入队会用到)
     */
    public Integer retryTimes;
}
