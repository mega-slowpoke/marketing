package org.example.domain.strategy.service;

public interface IStrategyInitializer {

    void initializeStrategy(Long strategyId);

    /**
    *  Do lottery, return an award ID
    * */
    Integer doLottery(Long strategyId);
}
