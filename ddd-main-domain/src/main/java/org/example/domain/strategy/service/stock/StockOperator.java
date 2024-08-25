package org.example.domain.strategy.service.stock;

import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.types.common.Constants;

import javax.annotation.Resource;

public class StockOperator implements IStockOperator{

    @Resource
    private IStrategyRepo iStrategyRepo;

    @Override
    public Boolean decrAwardStock(Long strategyId, Integer awardId) {
        String cacheKey = Constants.RedisKey.STRATEGY_AWARD_TOTAL_KEY + strategyId + Constants.UNDERLINE + awardId;
        return iStrategyRepo.decrAwardStock(cacheKey);
    }
}
