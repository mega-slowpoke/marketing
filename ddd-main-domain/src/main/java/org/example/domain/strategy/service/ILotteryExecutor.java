package org.example.domain.strategy.service;

public interface ILotteryExecutor {
    /**
     *  Do lottery, return an award ID
     * */
    Integer doLottery(Long strategyId);
}
