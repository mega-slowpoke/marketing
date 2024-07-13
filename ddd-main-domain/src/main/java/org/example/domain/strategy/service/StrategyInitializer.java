package org.example.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class StrategyInitializer implements IStrategyInitializer{

    @Resource
    private IStrategyRepo iStrategyRepo;

    @Override
    public void initializeStrategy(Long strategyId) {
        // 1. get strategy awards
        List<StrategyAwardEntity> strategyAwardEntityList = iStrategyRepo.queryStrategyAwardEntityList(strategyId);

        // if list is empty, we will get divide by zero error
        if (strategyAwardEntityList == null || strategyAwardEntityList.isEmpty()) {
            String errorStr = "No such strategy, initializeStrategy fails at" + StrategyInitializer.class.getCanonicalName();
            log.error(errorStr);
            throw new RuntimeException(errorStr);
        }


        // 2. create buckets for all award
        // find the minimum rate and total rate
        // 总概率除以最小的概率并且向上取整就能得到近似的总slot数，是因为我们设置的所有奖品概率之和是允许超过1的，所以要计算概率总和
        BigDecimal minRate = strategyAwardEntityList.stream().map(StrategyAwardEntity::getAwardRate).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        BigDecimal totalRate = strategyAwardEntityList.stream().map(StrategyAwardEntity::getAwardRate).reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. 用 totalRate % minRate 获得近似的总slot数
        BigDecimal rateRange = totalRate.divide(minRate, 0, RoundingMode.CEILING);

        // 5. 生成策略奖品概率查找表「这里指需要在list集合中，存放上对应的奖品占位即可，占位越多等于概率越高」
        Map<Integer, Long> awardDistribution = new ConcurrentHashMap<>();
        int idx = 0;
        for (StrategyAwardEntity strategyAward : strategyAwardEntityList) {
            Long awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            // 计算出每个概率值需要占用多少个slot (向上取整，最后得到的只是近似值）
            int slotNum = rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue();
            for (int i = 0; i < slotNum; i++) {
                awardDistribution.put(idx++, awardId);
            }
        }

        // 4. move map to redis

    }
}
