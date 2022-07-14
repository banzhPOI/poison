package org.poison.statemachine;

public interface StateMachine<S, E> {

    /**
     * 执行事件
     */
    S fireEvent(S sourceState, E event, Object ctx);

    String getMachineId();
}
