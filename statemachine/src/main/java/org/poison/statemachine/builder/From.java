package org.poison.statemachine.builder;

/**
 * From
 */
public interface From<S, E> {
    /**
     * Build transition target state and return to clause builder
     * @param stateId id of state
     * @return To clause builder
     */
    To<S, E> to(S stateId);

}
