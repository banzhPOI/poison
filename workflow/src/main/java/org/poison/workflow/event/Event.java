package org.poison.workflow.event;

import lombok.Data;
import org.poison.workflow.persister.BasePersister;

@Data
public abstract class Event<E> implements BasePersister<E> {

    private Long id;

    private String name;

}
