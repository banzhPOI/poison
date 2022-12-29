package org.poison.flow.order;

import org.poison.flow.flowTemplate.BaseFlow;
import org.poison.flow.node.BaseConditionNode;
import org.poison.flow.node.BaseEndNode;
import org.poison.flow.node.BaseNode;
import org.poison.flow.node.BaseStartNode;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class OrderFlow extends BaseFlow {


    /**
     * 流程名
     */
    @Override
    protected String getFlowName() {
        return "ORDER_FLOW";
    }

    /**
     * 节点Map
     */
    @Override
    protected Map<String, BaseNode> getNodeMap() {
        BaseStartNode orderCreate = new BaseStartNode();
        orderCreate.setNodeId("orderCreate");
        orderCreate.setTargetNodeId("orderPaid");

        BaseNode orderPaid = new BaseNode();
        orderCreate.setNodeId("orderPaid");
        orderPaid.setSourceNodeIdList(List.of("orderCreate"));
        orderPaid.setTargetNodeId("orderPaidCheck");

        BaseConditionNode orderPaidCheck = new BaseConditionNode();
        orderPaidCheck.setNodeId("orderPaidCheck");
        orderPaidCheck.setSourceNodeIdList(List.of("orderPaid"));
        orderPaidCheck.setTargetNodeIdList(List.of("orderComplete","orderCancel"));
        orderPaidCheck.setPredicate(new Predicate<>() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        });

        BaseEndNode orderComplete = new BaseEndNode();
        orderComplete.setNodeId("orderComplete");
        orderComplete.setSourceNodeIdList(List.of("orderPaidCheck"));

        BaseEndNode orderCancel = new BaseEndNode();
        orderComplete.setNodeId("orderCancel");
        orderComplete.setSourceNodeIdList(List.of("orderPaidCheck"));


        return null;
    }

    /**
     * 起始节点
     */
    @Override
    protected BaseStartNode getStartNode() {
        return null;
    }


}
