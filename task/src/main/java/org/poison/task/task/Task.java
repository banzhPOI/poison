package org.poison.task.task;

import lombok.Data;

import org.poison.merge.queue.ShardingBaseTask;
import org.poison.merge.set.BaseTask;

@Data
public class Task extends BaseTask {

    private Long id;

    private String content;

    /**
     * 获取唯一表示，用以去重
     * 示例：
     * public String getUniqueKey() {
     * return id;
     * }
     */
    @Override
    public String getUniqueKey() {
        return id.toString();
    }
}
