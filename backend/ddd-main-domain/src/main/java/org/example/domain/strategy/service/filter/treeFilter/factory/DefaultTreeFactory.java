package org.example.domain.strategy.service.filter.treeFilter.factory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.model.valobj.RuleTreeVO;
import org.example.domain.strategy.service.filter.treeFilter.ILogicTreeNode;
import org.example.domain.strategy.service.filter.treeFilter.factory.engine.IDecisionTreeEngine;
import org.example.domain.strategy.service.filter.treeFilter.factory.engine.impl.DecisionTreeEngine;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 规则树工厂
 * @create 2024-01-27 11:28
 */
@Service
public class DefaultTreeFactory {

    private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

    public DefaultTreeFactory(Map<String, ILogicTreeNode> logicTreeNodeGroup) {
        this.logicTreeNodeGroup = logicTreeNodeGroup;
    }

    public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO) {
        return new DecisionTreeEngine(logicTreeNodeGroup, ruleTreeVO);
    }

    /**
     * 决策树个动作实习
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeActionEntity {
        private RuleLogicCheckTypeVO ruleLogicCheckType;
        private StrategyAwardVO strategyAwardVO;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {
        /** 抽奖奖品ID - 内部流转使用 */
        private Integer awardId;
        /** 抽奖奖品规则 */
        private String awardRuleValue;

        private Integer sortOrder;
    }

}
