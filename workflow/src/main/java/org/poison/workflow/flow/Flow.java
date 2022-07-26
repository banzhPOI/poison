package org.poison.workflow.flow;

import lombok.Data;
import org.poison.workflow.action.Action;
import org.poison.workflow.event.Event;
import org.poison.workflow.persister.BasePersister;
import org.poison.workflow.status.Status;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用以构建过程
 */
@Data
public abstract class Flow<P, R, A, T extends Flow<P, R, A, T>> implements BasePersister<T> {


    @Resource
    private Action<P, R> baseAction;

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
     * 动作ClassName
     */
    String actionClassName;


    /**
     * 构造过程
     *
     * @param sourceStatusId
     * @param targetStatusId
     * @param eventId
     * @param actionClassName
     * @return
     */
    public abstract T build(Long sourceStatusId, Long targetStatusId, Long eventId, String actionClassName);

    /**
     * 检索过程(如果有需要可以考虑加缓存)
     *
     * @param sourceStatusId
     * @param eventId
     * @return
     */
    public abstract T find(Long sourceStatusId, Long eventId);


    /**
     * 检索过程(如果有需要可以考虑加缓存)
     *
     * @param sourceStatusId
     * @return
     */
    public abstract List<T> find(Long sourceStatusId);

    /**
     * 获取动作
     */
    public Action<P,R> getAction(String actionName) {
        return baseAction.getAction(actionName);
    }

    /**
     * 触发事件
     */
    public void fireEvent(Long sourceStatusId, Long eventId) {
        Flow<P, R, A, T> flow = find(sourceStatusId, eventId);
        flow.getAction(flow.getActionClassName()).doAction();
    }

    /**
     * 触发事件
     */
    public void fireEventWithParam(Long sourceStatusId, Long eventId, P param) {
        Flow<P, R, A, T> flow = find(sourceStatusId, eventId);
        flow.getAction(flow.getActionClassName()).doActionWithParam(param);
    }

    /**
     * 触发事件
     */
    public R fireEventWithResult(Long sourceStatusId, Long eventId, P param) {
        Flow<P, R, A, T> flow = find(sourceStatusId, eventId);
        return flow.getAction(flow.getActionClassName()).doActionWithResult(param);
    }

    public T build(Long sourceStatusId, Long targetStatusId, Long eventId, String actionClassName, Class<T> clazz) {
        T t;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        t.setSourceStatusId(sourceStatusId);
        t.setTargetStatusId(targetStatusId);
        t.setEventId(eventId);
        t.setActionClassName(actionClassName);
        return t;
    }
}
