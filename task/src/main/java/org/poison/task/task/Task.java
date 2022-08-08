package org.poison.task.task;

import lombok.Data;

import org.poison.merge.BaseTask;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Task extends BaseTask {

    Long id;

    /**
     * key一样视为重复
     */
    String key;
}
