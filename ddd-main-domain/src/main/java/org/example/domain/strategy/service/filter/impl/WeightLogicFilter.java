package org.example.domain.strategy.service.filter.impl;

import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.entity.FilterConditionEntity;
import org.example.domain.strategy.model.entity.StrategyRuleEntity;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.filter.ILogicFilter;
import org.example.types.common.Constants;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class WeightLogicFilter implements ILogicFilter<RuleActionEntity.RaffleBeforeEntity> {

    @Resource
    private IStrategyRepo iStrategyRepo;

    @Override
    public RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> filter(FilterConditionEntity filterConditionEntity) {
        String userId = filterConditionEntity.getUserId();
        Long strategyId = filterConditionEntity.getStrategyId();

        StrategyRuleEntity strategyRuleEntity = iStrategyRepo.queryStrategyRuleByIdAndName(strategyId, Constants.RuleName.RULE_WEIGHT);
        Map<Integer, List<Integer>> ruleWeightMap = strategyRuleEntity.getRuleWeightMap();

        Integer userTotalPoints = 4800;
        // 找出最小符合的值，也就是【4500 积分，能找到 4000:102,103,104,105】、【5000 积分，能找到 5000:102,103,104,105,106,107】
        Integer minPoint= Integer.MAX_VALUE;
        for (Integer pointThreshold : ruleWeightMap.keySet()) {
            if (pointThreshold <= userTotalPoints && pointThreshold < minPoint) {
                minPoint = pointThreshold;
            }
        }

        // 达到了最小积分要求，按照特殊规则处理
        if (minPoint != Integer.MAX_VALUE) {
            return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                    .data(RuleActionEntity.RaffleBeforeEntity.builder()
                            .strategyId(strategyId)   // !!!!!注意这里缺少了ruleWeightValueKey
                            .build())
                    .ruleModel(Constants.RuleName.RULE_WEIGHT)
                    .code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
                    .info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
                    .build();
        }

        return RuleActionEntity.<RuleActionEntity.RaffleBeforeEntity>builder()
                .code(RuleLogicCheckTypeVO.ALLOW.getCode())
                .info(RuleLogicCheckTypeVO.ALLOW.getInfo())
                .build();
    }


}