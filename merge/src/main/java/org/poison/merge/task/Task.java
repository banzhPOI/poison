package org.poison.merge.task;

import lombok.Data;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Task implements Serializable, Cloneable {

    Long id;

    /**
     * name一样视为重复
     */
    String name;


    @Override
    public Task clone() {
        try {
            Task clone = (Task) super.clone();
            // TODO: 复制此处的可变状态，这样此克隆就不能更改初始克隆的内部项
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

   public static List<Task> listClone(List<Task> sourceList) {
        List<Task> cloneList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sourceList)) {
            return cloneList;
        }
        sourceList.forEach(o -> {
            cloneList.add(o.clone());
        });
        return cloneList;
    }

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task that = (Task) o;
        return Objects.equals(name, that.name);
    }

    /**
     * 重写hashCode方法
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
