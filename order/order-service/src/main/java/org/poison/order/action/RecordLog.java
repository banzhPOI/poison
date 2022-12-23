package org.poison.order.action;

import lombok.extern.slf4j.Slf4j;
import org.poison.order.pojo.BasePojo.BaseDocDTO;
import org.poison.order.pojo.BasePojo.BaseEventRequest;
import org.poison.order.pojo.BasePojo.BaseParamAction;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RecordLog extends BaseParamAction {

    @Override
    public void doAction(BaseDocDTO docDTO, BaseEventRequest req) {
        log.info("log has bean Record");
    }
}
