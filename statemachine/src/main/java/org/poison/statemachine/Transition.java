package org.poison.statemachine;

public interface Transition<S, E> {

    S getFrom();

    void setFrom(S from);

    S getTo();

    void setTo(S to);

    E getEvent();

    void setEvent(E event);

    Action<S, E> getAction();

    void setAction(Action<S, E> action);
}
