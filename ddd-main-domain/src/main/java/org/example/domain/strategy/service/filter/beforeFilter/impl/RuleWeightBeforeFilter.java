package org.example.domain.strategy.service.filter.beforeFilter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.StrategyRuleEntity;
import org.example.domain.strategy.service.filter.beforeFilter.AbstractBeforeFilter;
import org.example.types.common.Constants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RuleWeightBeforeFilter extends AbstractBeforeFilter {

    private Integer userScore = 4800;

    @Override
    public Integer processBeforeFilterAndGetAwardId(String userId, Long strategyId) {
        StrategyRuleEntity strategyRuleEntity = iStrategyRepo.queryStrategyRuleByIdAndName(strategyId, Constants.RuleName.RULE_WEIGHT);
        Map<Integer, List<Integer>> ruleWeightMap = strategyRuleEntity.getRuleWeightMap();

        // 找出最小符合的值，也就是【4500 积分，能找到 4000:102,103,104,105】、【5000 积分，能找到 5000:102,103,104,105,106,107】
        int minPoint= Integer.MAX_VALUE;
        for (Integer pointThreshold : ruleWeightMap.keySet()) {
            if (pointThreshold <= userScore && pointThreshold < minPoint) {
                minPoint = pointThreshold;
            }
        }

        // 达到了最小积分要求，按照特殊规则处理
        if (minPoint != Integer.MAX_VALUE) {
            Integer awardId = iLotteryExecutor.doLottery(strategyId, String.valueOf(minPoint));
            log.info("抽奖责任链-权重接管 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, Constants.RuleName.RULE_WEIGHT, awardId);
            return awardId;
        }

        // 5. 过滤其他责任链
        log.info("抽奖责任链-权重放行 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, Constants.RuleName.RULE_WEIGHT);
        return next().processBeforeFilterAndGetAwardId(userId, strategyId);

    }
}
