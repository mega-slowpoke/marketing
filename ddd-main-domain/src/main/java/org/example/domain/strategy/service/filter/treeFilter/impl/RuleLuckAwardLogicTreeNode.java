package org.example.domain.strategy.service.filter.treeFilter.impl;


import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.service.filter.treeFilter.ILogicTreeNode;
import org.example.domain.strategy.service.filter.treeFilter.factory.DefaultTreeFactory;
import org.springframework.stereotype.Component;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 兜底奖励节点 Last resort
 * @create 2024-01-27 11:23
 */
@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                        .awardId(101)
                        .awardRuleValue("1,100")
                        .build())
                .build();
    }

}
