package org.poison.workflow.status;

import lombok.Data;
import org.poison.workflow.persister.BasePersister;

@Data
public abstract class Status<S> implements BasePersister<S> {

    private Long id;

    private String name;

}
