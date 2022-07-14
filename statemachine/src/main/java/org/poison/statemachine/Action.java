package org.poison.statemachine;

public interface Action<S, E> {

    void execute(S from, S to, E event, Object ctx);
}
