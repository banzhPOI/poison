package org.poison.document.template.action;

import org.poison.document.template.core.ctx.Context;
import org.poison.document.template.core.ctx.Event;
import org.poison.document.template.core.ctx.Status;
import org.poison.statemachine.Action;

public interface BizActionService {

    Action<Status, Event, Object> preHandle();

    Action<Status, Event, Object> execute();

    Action<Status, Event, Object> cancel();
}
