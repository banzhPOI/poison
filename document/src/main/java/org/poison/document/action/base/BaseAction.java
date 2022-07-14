package org.poison.document.action.base;

public interface BaseAction<O> {

    void init(O operate);

    void execute(O operate);

    void cancel(O operate);
}
