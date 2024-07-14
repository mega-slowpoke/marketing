package org.example.domain.strategy.service;

public interface LotteryExecutor {
    /**
     *  Do lottery, return an award ID
     * */
    Integer doLottery(Long strategyId);
}
