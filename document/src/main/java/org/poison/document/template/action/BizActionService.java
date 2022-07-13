package org.poison.document.template.action;

import org.poison.statemachine.Action;
import org.poison.document.template.core.DocumentItem;
import org.poison.document.template.core.DocumentItemEvent;
import org.poison.document.template.core.DocumentItemStatus;

public interface BizActionService {

    Action<DocumentItemStatus, DocumentItemEvent, DocumentItem> preHandle();

    Action<DocumentItemStatus, DocumentItemEvent, DocumentItem> execute();

    Action<DocumentItemStatus, DocumentItemEvent, DocumentItem> cancel();
}
