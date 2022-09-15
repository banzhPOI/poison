package org.poison.merge;

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

}
