package org.example.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.model.entity.StrategyEntity;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class StrategyInitializer implements IStrategyInitializer{

    @Resource
    private IStrategyRepo iStrategyRepo;


    @Override
    public Boolean initializeStrategy(Long strategyId) {
        // 1. get strategy awards
        List<StrategyAwardEntity> strategyAwardEntityList = iStrategyRepo.queryStrategyAwardEntityList(strategyId);

        // 2. generate award distribution 生成全部概率的中奖分布情况
        generateAwardDistribution(strategyId, strategyAwardEntityList);

        // 用户根据积累的积分，可以缩小中奖范围的，比如说总共积累了6000积分抽奖，那么接下来的抽奖固定会抽到103-109的奖品，不会让用户再抽到过低价值的奖品如101、102
        // 3. 如果该抽奖策略带有累计积分的规则，则生成各累计积分对应的中奖情况
        StrategyEntity strategyEntity = iStrategyRepo.queryStrategy(strategyId);
        // 该策略没有积累积分规则
        if (!strategyEntity.getRuleWeight()) return true;

        // 该策略有累计积分规则，生成各累计积分对应的中奖情况并放入redis
//        for (String str : ) {
//
//        }
//

        return null;
    }

    private void generateAwardDistribution(Long strategyId, List<StrategyAwardEntity> strategyAwardEntityList) {
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
        Map<Integer, Integer> awardDistribution = new ConcurrentHashMap<>();
        int idx = 0;
        for (StrategyAwardEntity strategyAward : strategyAwardEntityList) {
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();
            // 计算出每个概率值需要占用多少个slot (向上取整，最后得到的只是近似值）
            int slotNum = rateRange.multiply(awardRate).setScale(0, RoundingMode.CEILING).intValue();
            for (int i = 0; i < slotNum; i++) {
                awardDistribution.put(idx++, awardId);
            }
        }

        // 4. move map to redis
        iStrategyRepo.putAwardDistributionToRedis(strategyId, awardDistribution.size(), awardDistribution);

    }


}
