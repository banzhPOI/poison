package org.poison.merge;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class ShardingBaseTask implements Serializable {

    public abstract String getShardingKey();
}
