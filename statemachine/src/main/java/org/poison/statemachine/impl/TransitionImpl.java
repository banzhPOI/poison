package org.poison.statemachine.impl;

import lombok.Data;
import org.poison.statemachine.Action;
import org.poison.statemachine.Transition;

@Data
public class TransitionImpl<S, E> implements Transition<S, E> {

    private S from;

    private S to;

    private E event;

    private Action<S, E> action;
}
