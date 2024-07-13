package org.example.domain.strategy.repository;

import org.example.domain.strategy.model.entity.StrategyAwardEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IStrategyRepo {

    List<StrategyAwardEntity> queryStrategyAwardEntityList(Long strategyId);

    void putAwardDistributionToRedis(Long strategyId, Integer totalBucket, Map<Integer, Integer> awardDistribution);

    Integer getRange(Long strategyId);

    Integer getAwardIdFromDistributionMap(Long strategyId, Integer randomIdx);
}
