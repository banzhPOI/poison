package org.poison.workflow.action;

public interface Action<P, R> {

    void doAction();

    void doActionWithParam(P param);

    R doActionWithResult(P param);
}
