package org.poison.statemachine.builder;


public class StateMachineBuilderFactory {

    public static <S, E> StateMachineBuilder<S, E> create(){
        return new StateMachineBuilderImpl<>();
    }

}
