package org.example.domain.strategy.service;

import org.example.domain.strategy.repository.IStrategyRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;

@Service
public class LotteryExecutor implements ILotteryExecutor {

    @Resource
    private IStrategyRepo iStrategyRepo;

    @Override
    public Integer doLottery(Long strategyId) {
        Integer totalBucket = iStrategyRepo.getRange(strategyId);
        int randomIdx = new SecureRandom().nextInt(totalBucket);
        return iStrategyRepo.getAwardIdFromDistributionMap(strategyId, randomIdx);
    }
}
