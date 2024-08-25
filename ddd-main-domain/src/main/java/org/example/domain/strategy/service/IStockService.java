package org.example.domain.strategy.service;

import org.example.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import org.springframework.stereotype.Service;

public interface IStockService {
    /**
     * 获取奖品库存消耗队列
     *
     * @return 奖品库存Key信息
     * @throws InterruptedException 异常
     */
    StrategyAwardStockKeyVO getNextConsumedAwardFromQueue() throws InterruptedException;

    /**
     * 更新奖品库存消耗记录
     *
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     */
    void decrDBAwardCountByOne(Long strategyId, Integer awardId);
}
