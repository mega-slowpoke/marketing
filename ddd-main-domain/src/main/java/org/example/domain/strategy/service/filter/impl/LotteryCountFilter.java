package org.example.domain.strategy.service.filter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.AwardRuleEntity;
import org.example.domain.strategy.model.entity.FilterConditionEntity;
import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.filter.IFilter;
import org.example.types.common.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class LotteryCountFilter implements IFilter<RuleActionEntity.DuringLotteryEntity> {

    @Resource
    private IStrategyRepo iStrategyRepo;

    private final Integer userLotteryCount = 0;

    @Override
    public RuleActionEntity<RuleActionEntity.DuringLotteryEntity> filter(FilterConditionEntity filterConditionEntity) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} ruleModel:{}", filterConditionEntity.getUserId(), filterConditionEntity.getStrategyId(), Constants.RuleName.RULE_LOCK);

        AwardRuleEntity awardRuleEntity = iStrategyRepo.queryAwardRuleByIdAndName(filterConditionEntity.getStrategyId(), filterConditionEntity.getAwardId(), Constants.RuleName.RULE_LOCK);
        int awardUnlockThreshold = Integer.parseInt(awardRuleEntity.getRuleValue());

        if (userLotteryCount >= awardUnlockThreshold) {
            return RuleActionEntity.<RuleActionEntity.DuringLotteryEntity>builder()
                    .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                    .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                    .build();
        }

        // 用户抽奖次数不够，不能获得对应奖品
        return RuleActionEntity.<RuleActionEntity.DuringLotteryEntity>builder()
                .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                .build();
    }
}
