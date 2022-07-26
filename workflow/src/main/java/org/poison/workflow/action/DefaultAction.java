package org.poison.workflow.action;

import org.springframework.stereotype.Component;

@Component
public abstract class DefaultAction<P, R, A extends DefaultAction<P, R, A>> implements Action<P, R> {

    @Override
    public A getAction(String className) {
        try {
            Class<A> clazz = (Class<A>) Class.forName(className);
            A action = clazz.getDeclaredConstructor().newInstance();
            return action;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doAction() {

    }

    @Override
    public void doActionWithParam(P param) {

    }

    @Override
    public R doActionWithResult(P param) {
        return null;
    }
}
