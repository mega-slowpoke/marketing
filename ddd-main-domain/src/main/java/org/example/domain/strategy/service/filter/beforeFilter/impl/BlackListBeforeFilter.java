package org.example.domain.strategy.service.filter.beforeFilter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.StrategyRuleEntity;
import org.example.domain.strategy.service.filter.beforeFilter.AbstractBeforeFilter;
import org.example.types.common.Constants;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlackListBeforeFilter extends AbstractBeforeFilter {

    @Override
    public Integer processBeforeFilterAndGetAwardId(String userId, Long strategyId) {
        // 获取strategyEntity, 并且拿到其中的ruleValue
        StrategyRuleEntity strategyRuleEntity = iStrategyRepo.queryStrategyRuleByIdAndName(strategyId, Constants.RuleName.RULE_BLACKLIST);
        String ruleValue = strategyRuleEntity.getRuleValue();

        Integer blackListAwardId = Integer.parseInt(ruleValue.split(Constants.COLON)[0]);
        String blockedUserIdStr = ruleValue.split(Constants.COLON)[1];

        String[] blockedUserIds = blockedUserIdStr.split(Constants.COMMA);
        for (String blockedUserId : blockedUserIds) {
            // 如果是用户在黑名单，就返回一个Takeover(表示有特殊规则)的为BlackList的RuleAction
            if (blockedUserId.equals(userId)) {
                log.info("抽奖责任链-黑名单接管 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, Constants.RuleName.RULE_BLACKLIST, blackListAwardId);
                return blackListAwardId;
            }
        }

        return next().processBeforeFilterAndGetAwardId(userId, strategyId);
    }
}
