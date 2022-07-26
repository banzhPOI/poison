package org.poison.document.transition;

import org.poison.document.action.ApplicationAction1;
import org.poison.workflow.action.Action;
import org.poison.workflow.flow.Flow;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ApplicationFlow extends Flow<Object, Object, Action, ApplicationFlow> {



    /**
     * 构建
     *
     * @param sourceStatusId
     * @param targetStatusId
     * @param eventId
     * @param actionClassName
     * @return
     */
    @Override
    public ApplicationFlow build(Long sourceStatusId, Long targetStatusId, Long eventId, String actionClassName) {
        return build(sourceStatusId, targetStatusId, eventId, actionClassName, ApplicationFlow.class);
    }

    /**
     * 检索过程(如果有需要可以考虑加缓存)
     *
     * @param sourceStatusId
     * @param eventId
     * @return
     */
    @Override
    public ApplicationFlow find(Long sourceStatusId, Long eventId) {
        /**
         * for test
         */
        ApplicationFlow flow = new ApplicationFlow();
        if (sourceStatusId==1L&&eventId==1L){
            flow.setEventId(1L);
            flow.setSourceStatusId(1L);
            flow.setTargetStatusId(2L);
        } else if (sourceStatusId==1L&&eventId==2L) {
            flow.setEventId(2L);
            flow.setSourceStatusId(1L);
            flow.setTargetStatusId(3L);
        }
        flow.setActionClassName(ApplicationAction1.class.getName());
        return flow;
    }

    /**
     * 检索过程(如果有需要可以考虑加缓存)
     *
     * @param sourceStatusId
     * @return
     */
    @Override
    public List<ApplicationFlow> find(Long sourceStatusId) {
        return null;
    }


    /**
     * 增加
     *
     * @param applicationTransition
     */
    @Override
    public void add(ApplicationFlow applicationTransition) {

    }

    /**
     * 删除
     * 删除前需要确认未在使用中
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {

    }

    /**
     * 更新
     *
     * @param applicationTransition
     */
    @Override
    public void update(ApplicationFlow applicationTransition) {

    }

    /**
     * 获取列表
     */
    @Override
    public List<ApplicationFlow> getList() {
        return null;
    }

    /**
     * 根据id查找
     *
     * @param id
     */
    @Override
    public ApplicationFlow findById(Long id) {
        return null;
    }


}
