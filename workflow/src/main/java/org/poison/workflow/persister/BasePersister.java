package org.poison.workflow.persister;

import java.util.List;

public interface BasePersister<T> {

    /**
     * 增加
     */
    void add(T t);

    /**
     * 删除
     * 删除前需要确认未在使用中
     */
    void deleteById(Long id);

    /**
     * 更新
     */
    void update(T t);

    /**
     * 获取列表
     */
    List<T> getList();

    /**
     * 根据id查找
     */
    T findById(Long id);
}
