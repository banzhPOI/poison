package org.poison.workflow.event;

import lombok.Data;
import org.poison.workflow.BasePersister;

@Data
public abstract class Event<E> implements BasePersister<E> {

    private Long id;

    private String name;

}
