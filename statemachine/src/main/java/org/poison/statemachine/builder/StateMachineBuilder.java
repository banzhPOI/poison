package org.poison.statemachine.builder;


import org.poison.statemachine.StateMachine;

/**
 * 状态机构造器
 */
public interface StateMachineBuilder<S,E> {

    /**
     * 跳转状态+单一来源状态
     */
    ExternalTransitionBuilder<S, E> externalTransition();

    /**
     * 不跳转状态+单一来源状态
     */
    InternalTransitionBuilder<S, E> internalTransition();


    /**
     * 构造
     */
    StateMachine<S,E> build(String machineId);
}
