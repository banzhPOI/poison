package org.poison.task.task;

import lombok.Data;

import org.poison.compactqueue.ShardingBaseTask;

@Data
public class Task extends ShardingBaseTask {

    private Long id;

    private String shardingKey;

    private String content;

    /**
     * 获取唯一表示，用以去重
     * 示例：
     * <pre class="code">
     * public String getUniqueKey() {
     *      return id;
     * } </pre>
     */
    @Override
    public String getUniqueKey() {
        return id.toString();
    }

    /**
     * 获取分片key
     */
    @Override
    public String getShardingKey() {
        return shardingKey;
    }

}
