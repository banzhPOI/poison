package org.poison.merge;

import java.util.List;

public interface Merger<T> {

    void add(T t);

    List<T> get();
}
