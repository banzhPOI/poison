package org.poison.order.pojo.BasePojo;

public abstract class BaseParamAction extends BaseAction {

    public abstract void doAction(BaseDocDTO docDTO, BaseEventRequest req);
}
