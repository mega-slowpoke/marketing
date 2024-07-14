package org.example.domain.strategy.repository;

import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.model.entity.StrategyEntity;

import java.util.List;
import java.util.Map;

public interface IStrategyRepo {

    List<StrategyAwardEntity> queryStrategyAwardEntityList(Long strategyId);

    void putAwardDistributionToRedis(String strategyKey, Integer totalBucket, Map<Integer, Integer> awardDistribution);

    Integer getRange(Long strategyId);

    Integer getAwardIdFromDistributionMap(Long strategyId, Integer randomIdx);

    StrategyEntity queryStrategyById(Long strategyId);
}
