package org.poison.statemachine.builder;



import org.poison.statemachine.StateMachine;
import org.poison.statemachine.StateMachineFactory;
import org.poison.statemachine.Transition;
import org.poison.statemachine.impl.StateMachineImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StateMachineBuilderImpl<S, E> implements StateMachineBuilder<S, E> {

    private final Map<Long, Transition<S, E>> stateMap = new ConcurrentHashMap<>();

    private final StateMachineImpl<S, E> stateMachine = new StateMachineImpl<>(stateMap);

    /**
     * Builder for one transition
     *
     * @return External transition builder
     */
    @Override
    public ExternalTransitionBuilder<S, E> externalTransition() {
        return new TransitionBuilderImpl<>(stateMap, TransitionType.EXTERNAL);
    }

    /**
     * Start to build internal transition
     *
     * @return Internal transition builder
     */
    @Override
    public InternalTransitionBuilder<S, E> internalTransition() {
        return new TransitionBuilderImpl<>(stateMap, TransitionType.INTERNAL);
    }

    @Override
    public StateMachine<S, E> build(String machineId) {
        stateMachine.setMachineId(machineId);
        StateMachineFactory.register(stateMachine);
        return stateMachine;
    }
}
