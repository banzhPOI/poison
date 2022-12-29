package org.poison.flow.node;

import java.util.List;

/**
 * 基础节点
 */
public class BaseNode {

    private String nodeId;

    private List<String> sourceNodeIdList;

    private String targetNodeId;


    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public List<String> getSourceNodeIdList() {
        return sourceNodeIdList;
    }

    public void setSourceNodeIdList(List<String> sourceNodeIdList) {
        this.sourceNodeIdList = sourceNodeIdList;
    }

    public String getTargetNodeId() {
        return targetNodeId;
    }

    public void setTargetNodeId(String targetNodeId) {
        this.targetNodeId = targetNodeId;
    }
}