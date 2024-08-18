package org.example.domain.strategy.service.filter.beforeFilter;

import org.example.domain.strategy.service.filter.beforeFilter.factory.BeforeFilterFactory;

public interface IBeforeFilter {

    // 执行一系列的抽奖前拦截规则，并且返回抽奖Id
    BeforeFilterFactory.StrategyAwardVO processBeforeFilterAndGetAwardId(String userId, Long strategyId);
}
