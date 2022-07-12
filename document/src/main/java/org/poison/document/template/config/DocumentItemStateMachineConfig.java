package org.poison.document.template.config;

import org.poison.document.template.core.DocumentItem;
import org.poison.document.template.core.DocumentItemEvent;
import org.poison.document.template.core.DocumentItemStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

@Configuration
@EnableStateMachine(name = "documentItemStateMachine")
public class DocumentItemStateMachineConfig extends StateMachineConfigurerAdapter<DocumentItemStatus, DocumentItemEvent> {

    /**
     * 配置状态
     */
    public void configure(StateMachineStateConfigurer<DocumentItemStatus, DocumentItemEvent> states) throws Exception {
        states
                .withStates()
                .initial(DocumentItemStatus.CREATE)
                .states(EnumSet.allOf(DocumentItemStatus.class));
    }

    /**
     * 配置状态转换事件关系
     */
    public void configure(StateMachineTransitionConfigurer<DocumentItemStatus, DocumentItemEvent> transitions) throws Exception {
        transitions
                .withExternal().source(DocumentItemStatus.CREATE).target(DocumentItemStatus.PENDING).event(DocumentItemEvent.INIT)
                .and()
                .withExternal().source(DocumentItemStatus.PENDING).target(DocumentItemStatus.COMPLETE).event(DocumentItemEvent.EXECUTE)
                .and()
                .withExternal().source(DocumentItemStatus.PENDING).target(DocumentItemStatus.INVALID).event(DocumentItemEvent.CANCEL);
    }

    /**
     * 持久化配置
     * 实际使用中，可以配合redis等，进行持久化操作
     */
    @Bean
    public DefaultStateMachinePersister persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<Object, Object, DocumentItem>() {
            @Override
            public void write(StateMachineContext<Object, Object> context, DocumentItem order) {
                //此处并没有进行持久化操作
            }

            @Override
            public StateMachineContext<Object, Object> read(DocumentItem order) {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                return new DefaultStateMachineContext(order.getItemStatus(), null, null, null);
            }
        });
    }
}
