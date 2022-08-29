package org.poison.merge.queue;

import lombok.Data;

@Data
public abstract class ShardingBaseTask extends BaseTask {

    public abstract String getShardingKey();
}
