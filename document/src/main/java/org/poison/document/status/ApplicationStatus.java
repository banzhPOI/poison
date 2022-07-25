package org.poison.document.status;

import org.poison.workflow.status.Status;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ApplicationStatus extends Status<ApplicationStatus> {

    /**
     * 增加
     *
     * @param status
     */
    @Override
    public void add(ApplicationStatus status) {

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
     * @param status
     */
    @Override
    public void update(ApplicationStatus status) {

    }

    /**
     * 获取列表
     */
    @Override
    public List<ApplicationStatus> getList() {
        return null;
    }

    /**
     * 根据id查找
     *
     * @param id
     */
    @Override
    public ApplicationStatus findById(Long id) {
        return null;
    }
}
