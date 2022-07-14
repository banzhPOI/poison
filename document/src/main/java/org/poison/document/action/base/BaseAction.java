package org.poison.document.action.base;

import org.poison.statemachine.Action;

public interface BaseAction<S, E> {

    Action<S, E> init();

    Action<S, E> execute();

    Action<S, E> cancel();
}
