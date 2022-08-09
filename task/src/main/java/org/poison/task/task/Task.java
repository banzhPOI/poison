package org.poison.task.task;

import lombok.Data;

import org.poison.merge.BaseTask;
import org.poison.merge.ShardingBaseTask;

import java.util.Objects;

@Data
public class Task extends ShardingBaseTask {


    Long id;

    String shardingKey;
}
