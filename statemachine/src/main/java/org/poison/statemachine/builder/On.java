package org.poison.statemachine.builder;


import org.poison.statemachine.Action;

/**
 * On
 */
public interface On<S, E>{
    /**
     * Define action to be performed during transition
     *
     * @param action performed action
     */
    void perform(Action<S, E> action);
}
