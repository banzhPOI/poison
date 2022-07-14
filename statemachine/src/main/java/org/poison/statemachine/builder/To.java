package org.poison.statemachine.builder;

/**
 * To
 */
public interface To<S, E> {
    /**
     * Build transition event
     * @param event transition event
     * @return On clause builder
     */
    On<S, E> on(E event);
}
