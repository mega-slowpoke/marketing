package org.example.domain.strategy.service.filter.treeFilter.impl;


import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.AwardRuleEntity;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.filter.treeFilter.ILogicTreeNode;
import org.example.domain.strategy.service.filter.treeFilter.factory.DefaultTreeFactory;
import org.example.types.common.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 次数锁节点
 * @create 2024-01-27 11:22
 */
@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {
    private Long userLotteryCount = 10L;

    @Resource
    private IStrategyRepo iStrategyRepo;

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} ruleModel:{}", userId, strategyId, Constants.RuleName.RULE_LOCK);

        AwardRuleEntity awardRuleEntity = iStrategyRepo.queryAwardRuleByIdAndName(strategyId, awardId, Constants.RuleName.RULE_LOCK);
        int awardUnlockThreshold = Integer.parseInt(awardRuleEntity.getRuleValue());

        // 用户抽奖次数大于规则限定值，规则放行
        if (userLotteryCount >= awardUnlockThreshold) {
            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                    .build();
        }

        // 用户抽奖次数不够，不能获得对应奖品
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                .build();
    }

}
