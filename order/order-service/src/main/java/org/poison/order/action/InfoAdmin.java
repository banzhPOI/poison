package org.poison.order.action;

import lombok.extern.slf4j.Slf4j;
import org.poison.order.pojo.BasePojo.BaseDocDTO;
import org.poison.order.pojo.BasePojo.BaseNonParamAction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InfoAdmin extends BaseNonParamAction {

    @Async
    @Override
    public void doAction(BaseDocDTO docDTO) {
        log.info("admin has bean notified doc id : {}", docDTO.getId());
    }
}
