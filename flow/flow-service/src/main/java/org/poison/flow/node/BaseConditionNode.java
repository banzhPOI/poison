package org.poison.flow.node;

import java.util.List;
import java.util.function.Predicate;

/**
 * 判断节点
 */
public class BaseConditionNode extends BaseNode {

    private List<String> targetNodeIdList;


    private Predicate predicate;


    private String doCondition() {
        return (String) targetNodeIdList.stream().filter(predicate).findFirst().get();
    }


    @Override
    public String getTargetNodeId() {
        return doCondition();
    }


    public void setTargetNodeIdList(List<String> targetNodeIdList) {
        this.targetNodeIdList = targetNodeIdList;
    }


    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }
}
