package org.poison.workflow.transition;

import lombok.Data;
import org.poison.workflow.BasePersister;

/**
 * 用以构建过程
 */
@Data
public abstract class Transition<T extends Transition> implements BasePersister<T> {

    /**
     * 来源状态id
     */
    Long sourceStatusId;

    /**
     * 目标状态id
     */
    Long targetStatusId;

    /**
     * 事件id
     */
    Long eventId;

    /**
     * 动作id
     */
    Long actionId;


    public abstract T build(Long sourceStatusId, Long targetStatusId, Long eventId, Long actionId);

    public T build(Long sourceStatusId, Long targetStatusId, Long eventId, Long actionId, Class<T> clazz) {
        T t;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        t.setSourceStatusId(sourceStatusId);
        t.setTargetStatusId(targetStatusId);
        t.setEventId(eventId);
        t.setActionId(actionId);
        return t;
    }
}
