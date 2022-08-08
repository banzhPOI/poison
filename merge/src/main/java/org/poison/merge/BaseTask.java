package org.poison.merge;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public abstract class BaseTask implements Serializable {

    Long id;

    /**
     * key一样视为重复
     */
    String key;

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTask that = (BaseTask) o;
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
