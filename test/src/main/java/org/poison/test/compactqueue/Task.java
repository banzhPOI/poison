package org.poison.test.compactqueue;

import lombok.Data;
import org.poison.compactqueue.ShardingBaseTask;

@Data
public class Task extends ShardingBaseTask {

    private String id;

    @Override
    public String getUniqueKey() {
        return id;
    }

    @Override
    public String getShardingKey() {
        return id;
    }
}
