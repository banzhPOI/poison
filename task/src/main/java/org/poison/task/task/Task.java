package org.poison.task.task;

import lombok.Data;

import org.poison.merge.queue.ShardingBaseTask;

@Data
public class Task extends ShardingBaseTask {


    private Long id;

    private String shardingKey;
}
