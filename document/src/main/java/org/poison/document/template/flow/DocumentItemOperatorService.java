package org.poison.document.template.flow;

import org.poison.document.template.core.DocumentItem;

public interface DocumentItemOperatorService {

    DocumentItem init(String userId,Long id);

    DocumentItem execute(String userId,Long id);

    DocumentItem cancel(String userId,Long id);

}
