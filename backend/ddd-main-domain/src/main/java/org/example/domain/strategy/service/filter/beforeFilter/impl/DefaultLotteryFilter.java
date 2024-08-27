package org.example.domain.strategy.service.filter.beforeFilter.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.service.filter.beforeFilter.AbstractBeforeFilter;
import org.example.domain.strategy.service.filter.beforeFilter.factory.BeforeFilterFactory;
import org.example.types.common.Constants;
import org.springframework.stereotype.Component;

/**
 * 没有任何过滤规则，直接进行抽奖
 */
@Slf4j
@Component
public class DefaultLotteryFilter extends AbstractBeforeFilter {

    @Override
    public BeforeFilterFactory.StrategyAwardVO processBeforeFilterAndGetAwardId(String userId, Long strategyId) {
        BeforeFilterFactory.StrategyAwardVO strategyAwardVO = new BeforeFilterFactory.StrategyAwardVO();
        Integer awardId = iLotteryExecutor.doLottery(strategyId);
        log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, Constants.RuleName.DEFAULT_RULE, awardId);
        strategyAwardVO.setAwardId(awardId);
        strategyAwardVO.setRuleModel(Constants.RuleName.DEFAULT_RULE);
        return strategyAwardVO;
    }
}
