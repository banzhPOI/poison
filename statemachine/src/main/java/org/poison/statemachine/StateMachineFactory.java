package org.poison.statemachine;

import org.poison.common.exception.SysException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StateMachineFactory {

    static Map<String /* machineId */, StateMachine> stateMachineMap = new ConcurrentHashMap<>();


    public static <S, E> void register(StateMachine<S, E> stateMachine) {
        String machineId = stateMachine.getMachineId();
        if (stateMachineMap.get(machineId) != null) {
            throw new SysException("The state machine with id [" + machineId + "] is already built, no need to build again");
        }
        stateMachineMap.put(stateMachine.getMachineId(), stateMachine);
    }

    public static <S, E> StateMachine<S, E> get(String machineId) {
        StateMachine stateMachine = stateMachineMap.get(machineId);
        if (stateMachine == null) {
            throw new SysException("There is no stateMachine instance for " + machineId + ", please build it first");
        }
        return stateMachine;
    }
}
