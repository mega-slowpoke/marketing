package org.example.domain.strategy.service.filter.beforeFilter;

public interface IBeforeFilter {

    // 执行一系列的抽奖前拦截规则，并且返回抽奖Id
    Integer processBeforeFilterAndGetAwardId(String userId, Long strategyId);
}
