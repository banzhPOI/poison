package org.poison.document.template.action;

import org.poison.document.template.core.ctx.Context;
import org.poison.document.template.core.ctx.Event;
import org.poison.document.template.core.ctx.Status;
import org.poison.statemachine.Action;

public interface BizActionService {

    Action<Status, Event, Context> preHandle();

    Action<Status, Event, Context> execute();

    Action<Status, Event, Context> cancel();
}
