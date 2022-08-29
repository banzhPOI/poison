package org.poison.merge;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class ShardingBaseTask extends BaseTask {

    public abstract String getShardingKey();
}
