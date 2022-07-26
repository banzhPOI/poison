package org.poison.workflow.action;

public interface Action<P, R> {

    Action<P,R> getAction(String className);

    void doAction();

    void doActionWithParam(P param);

    R doActionWithResult(P param);
}
