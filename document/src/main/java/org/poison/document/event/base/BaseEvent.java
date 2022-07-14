package org.poison.document.event.base;

public interface BaseEvent {

    /**
     * 初始化
     * @param userId
     * @param id
     */
    void init(String userId, String id);

    /**
     * 执行
     * @param userId
     * @param id
     */
    void execute(String userId, String id);

    /**
     * 取消
     * @param userId
     * @param id
     */
    void cancel(String userId, String id);

    /**
     * 回退
     * @param userId
     * @param id
     */
    void rollback(String userId, String id);

    /**
     * 删除
     * @param userId
     * @param id
     */
    void delete(String userId, String id);
}
