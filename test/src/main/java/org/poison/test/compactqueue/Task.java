package org.poison.test.compactqueue;

import lombok.Data;
import org.poison.compactqueue.BaseTask;

@Data
public class Task extends BaseTask {

    private String id;

    @Override
    public String getUniqueKey() {
        return id;
    }
}
