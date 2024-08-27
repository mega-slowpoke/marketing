package org.example.domain.strategy.service.executor;

public interface ILotteryExecutor {
    /**
     *  Do lottery, return an award ID
     * */
    Integer doLottery(Long strategyId);

    Integer doLottery(Long strategyId, String ruleWeightVal);
}
