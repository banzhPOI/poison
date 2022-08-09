package org.poison.merge;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public abstract class BaseTask implements Serializable {

    Long id;


    /**
     * 重写equals方法
     */
    @Override
    public abstract boolean equals(Object o);

    /**
     * 重写hashCode方法
     */
    @Override
    public abstract int hashCode();
}
