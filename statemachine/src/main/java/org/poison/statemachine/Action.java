package org.poison.statemachine;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Action<S, E> {

    void execute(S from, S to, E event, Object ctx) ;
}
