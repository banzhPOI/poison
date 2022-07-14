package org.poison.document.event.inboundOrder;

public class InboundOrderItemEventImpl implements InboundOrderItemEvent{

    /**
     * 初始化
     *
     * @param userId
     * @param id
     */
    @Override
    public void init(String userId, String id) {

    }

    /**
     * 执行
     *
     * @param userId
     * @param id
     */
    @Override
    public void execute(String userId, String id) {

    }

    /**
     * 取消
     *
     * @param userId
     * @param id
     */
    @Override
    public void cancel(String userId, String id) {

    }

    /**
     * 回退
     *
     * @param userId
     * @param id
     */
    @Override
    public void rollback(String userId, String id) {

    }

    /**
     * 删除
     *
     * @param userId
     * @param id
     */
    @Override
    public void delete(String userId, String id) {

    }

    /**
     * 部分入库
     *
     * @param userId
     * @param id       入库单项id
     * @param quantity 部分入库数量
     * @param finish   结束入库
     */
    @Override
    public void partExecute(String userId, Long id, Long quantity, Boolean finish) {

    }
}
