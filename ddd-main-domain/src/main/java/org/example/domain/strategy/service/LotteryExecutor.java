package org.example.domain.strategy.service;

import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.types.common.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;

@Service
public class LotteryExecutor implements ILotteryExecutor {

    @Resource
    private IStrategyRepo iStrategyRepo;

    @Override
    public Integer doLottery(Long strategyId) {
        Integer totalBucket = iStrategyRepo.getRange(String.valueOf(strategyId));
        int randomIdx = new SecureRandom().nextInt(totalBucket);
        return iStrategyRepo.getAwardIdFromDistributionMap(String.valueOf(strategyId), randomIdx);
    }

    @Override
    public Integer doLottery(Long strategyId, String ruleWeightVal) {
        String bucketTotalKey = Constants.RedisKey.STRATEGY_TOTAL_BUCKET_KEY.concat(String.valueOf(strategyId)).concat("_").concat(ruleWeightVal);
        Integer totalBucket = iStrategyRepo.getRange(bucketTotalKey);
        int randomIdx = new SecureRandom().nextInt(totalBucket);
        String awardDistributionKey = Constants.RedisKey.AWARD_DISTRIBUTION_KEY.concat(String.valueOf(strategyId)).concat("_").concat(ruleWeightVal);
        return iStrategyRepo.getAwardIdFromDistributionMap(awardDistributionKey, randomIdx);
    }
}
