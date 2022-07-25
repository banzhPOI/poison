package org.poison.document.event;

import org.poison.workflow.event.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationEvent extends Event<ApplicationEvent> {


    /**
     * 增加
     *
     * @param event
     */
    @Override
    public void add(ApplicationEvent event) {

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
     * @param event
     */
    @Override
    public void update(ApplicationEvent event) {

    }

    /**
     * 获取列表
     */
    @Override
    public List<ApplicationEvent> getList() {
        return null;
    }

    /**
     * 根据id查找
     *
     * @param id
     */
    @Override
    public ApplicationEvent findById(Long id) {
        return null;
    }
}
