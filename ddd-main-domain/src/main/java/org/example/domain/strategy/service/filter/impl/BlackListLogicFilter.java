package org.example.domain.strategy.service.filter.impl;

import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.entity.FilterConditionEntity;
import org.example.domain.strategy.model.entity.StrategyRuleEntity;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.filter.ILogicFilter;
import org.example.types.common.Constants;

import javax.annotation.Resource;

public class BlackListLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepo iStrategyRepo;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(FilterConditionEntity filterConditionEntity) {
        String userId = filterConditionEntity.getUserId();
        Long strategyId = filterConditionEntity.getStrategyId();

        // 获取strategyEntity, 并且拿到其中的ruleValue,
        StrategyRuleEntity strategyRuleEntity = iStrategyRepo.queryStrategyRuleByIdAndName(strategyId, Constants.RuleName.RULE_BLACKLIST);
        String ruleValue = strategyRuleEntity.getRuleValue();

        String awardId = ruleValue.split(Constants.COLON)[0];
        String blockedUserIdStr = ruleValue.split(Constants.COLON)[1];

        String[] blockedUserIds = blockedUserIdStr.split(Constants.COMMA);
        for (String blockedUserId : blockedUserIds) {
            // 如果是用户在黑名单，就返回一个Takeover(表示有特殊规则)的为BlackList的RuleAction
            if (blockedUserId.equals(userId)) {
                return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                        .ruleModel(Constants.RuleName.RULE_BLACKLIST)
                        .data(RuleActionEntity.RaffleBeforeEntity.builder()
                                .strategyId(strategyId)
                                .build())
                        .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                        .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                        .build();
            }
        }

        // 否则返回一个普通的没有特殊规则的RuleAction
        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();

    }
}
