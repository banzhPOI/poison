package org.poison.statemachine.impl;

import lombok.Getter;
import lombok.Setter;
import org.poison.statemachine.Action;
import org.poison.statemachine.StateMachine;
import org.poison.statemachine.Transition;

import java.util.Map;

public class StateMachineImpl<S, E> implements StateMachine<S, E> {

    @Getter
    @Setter
    private String machineId;

    private final Map<Long, Transition<S, E>> stateMap;

    public StateMachineImpl(Map<Long, Transition<S, E>> stateMap) {
        this.stateMap = stateMap;
    }

    @Override
    public S fireEvent(S sourceState, E event, Object ctx) {
        long hash = sourceState.hashCode()+event.hashCode();
        Transition<S, E> transition = stateMap.get(hash);
        Action<S, E> action = transition.getAction();
        action.execute(transition.getFrom(),transition.getTo(),transition.getEvent(),ctx);
        return transition.getTo();
    }
}
