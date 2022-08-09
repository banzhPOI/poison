package org.poison.task.task;

import lombok.Data;

import org.poison.merge.BaseTask;

import java.util.Objects;

@Data
public class Task extends BaseTask {

    /**
     * key一样视为重复
     */
    String key;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task that = (Task) o;
        return Objects.equals(key, that.key);
    }

    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
