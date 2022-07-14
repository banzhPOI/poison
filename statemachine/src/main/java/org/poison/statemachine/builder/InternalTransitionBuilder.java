package org.poison.statemachine.builder;

/**
 * InternalTransitionBuilder
 */
public interface InternalTransitionBuilder <S, E> {
    /**
     * Build a internal transition
     * @param stateId id of transition
     * @return To clause builder
     */
    To<S, E> within(S stateId);
}
