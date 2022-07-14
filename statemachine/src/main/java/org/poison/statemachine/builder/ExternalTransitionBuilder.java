package org.poison.statemachine.builder;

/**
 * ExternalTransitionBuilder
 */
public interface ExternalTransitionBuilder<S, E> {
    /**
     * Build transition source state.
     * @param stateId id of state
     * @return from clause builder
     */
    From<S, E> from(S stateId);

}
