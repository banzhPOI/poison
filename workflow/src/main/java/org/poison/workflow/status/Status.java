package org.poison.workflow.status;

import lombok.Data;
import org.poison.workflow.BasePersister;

@Data
public abstract class Status<S> implements BasePersister<S> {

    private Long id;

    private String name;

}
