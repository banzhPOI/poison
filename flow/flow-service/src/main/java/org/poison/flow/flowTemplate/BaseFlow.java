package org.poison.flow.flowTemplate;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.poison.flow.node.BaseNode;
import org.poison.flow.node.BaseStartNode;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public abstract class BaseFlow {

    private RedissonClient redisson;

    @Autowired
    public final void setRedisson(RedissonClient redisson) {
        this.redisson = redisson;
    }

    public static final String FLOW_TEMPLATE_CACHE_NAME = "flow:flow-template-cache";

    /**
     * 初始化
     */
    @PostConstruct
    void postConstruct() {
        RMapCache<String, Map<String, BaseNode>> flowTemplateCacheMap = redisson.getMapCache(FLOW_TEMPLATE_CACHE_NAME);
        flowTemplateCacheMap.put(getFlowName(), getNodeMap());
    }

    /**
     * 流程名
     */
    protected abstract String getFlowName();

    /**
     * 节点Map
     */
    protected abstract Map<String, BaseNode> getNodeMap();

    /**
     * 起始节点
     */
    protected abstract BaseStartNode getStartNode();


}
