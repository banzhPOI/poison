package org.poison.document.transition;

import org.poison.workflow.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationTransition extends Transition<ApplicationTransition> {

    /**
     * 构建
     * @param sourceStatusId
     * @param targetStatusId
     * @param eventId
     * @param actionId
     * @return
     */
    @Override
    public ApplicationTransition build(Long sourceStatusId, Long targetStatusId, Long eventId, Long actionId) {
        return build(sourceStatusId, targetStatusId, eventId, actionId,ApplicationTransition.class);
    }

    /**
     * 增加
     *
     * @param applicationTransition
     */
    @Override
    public void add(ApplicationTransition applicationTransition) {

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
    public void update(ApplicationTransition applicationTransition) {

    }

    /**
     * 获取列表
     */
    @Override
    public List<ApplicationTransition> getList() {
        return null;
    }

    /**
     * 根据id查找
     *
     * @param id
     */
    @Override
    public ApplicationTransition findById(Long id) {
        return null;
    }



}
