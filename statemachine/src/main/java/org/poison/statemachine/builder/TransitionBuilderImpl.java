package org.poison.statemachine.builder;

import org.poison.statemachine.Action;
import org.poison.statemachine.Transition;
import org.poison.statemachine.impl.TransitionImpl;

import java.util.Map;

public class TransitionBuilderImpl<S, E> implements ExternalTransitionBuilder<S, E>, InternalTransitionBuilder<S, E>, From<S, E>, On<S, E>, To<S, E> {

    final Map<Long, Transition<S, E>> stateMap;

    private S source;

    protected S target;

    private Transition<S, E> transition;

    final TransitionType transitionType;

    public TransitionBuilderImpl(Map<Long, Transition<S, E>> stateMap, TransitionType transitionType) {
        this.stateMap = stateMap;
        this.transitionType = transitionType;
    }

    @Override
    public From<S, E> from(S stateId) {
        transition = new TransitionImpl<S, E>();
        transition.setFrom(stateId);
        source = stateId;
        return this;
    }

    @Override
    public To<S, E> to(S stateId) {
        transition.setTo(stateId);
        target = stateId;
        return this;
    }

    @Override
    public To<S, E> within(S stateId) {
        transition.setFrom(stateId);
        transition.setTo(stateId);
        source = target = stateId;
        return this;
    }

    @Override
    public On<S, E> on(E event) {
        transition.setEvent(event);
        return this;
    }

    @Override
    public void perform(Action<S, E> action) {
        transition.setAction(action);
        long hash = source.hashCode() + transition.getEvent().hashCode();
        stateMap.put(hash, transition);
    }
}
