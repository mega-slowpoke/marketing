package org.example.domain.strategy.repository;

import org.example.domain.strategy.model.entity.AwardRuleEntity;
import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.model.entity.StrategyEntity;
import org.example.domain.strategy.model.entity.StrategyRuleEntity;
import org.example.domain.strategy.model.valobj.RuleTreeVO;
import org.example.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import org.example.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

import java.util.List;
import java.util.Map;

public interface IStrategyRepo {


    List<StrategyAwardEntity> queryStrategyAwardEntityList(Long strategyId);

    void putAwardDistributionToRedis(String strategyKey, Integer totalBucket, Map<Integer, Integer> awardDistribution);

    Integer getRange(String strategyKey);

    Integer getAwardIdFromDistributionMap(String strategyKey, Integer randomIdx);

    StrategyEntity queryStrategyById(Long strategyId);

    StrategyRuleEntity queryStrategyRuleByIdAndName(Long strategyId, String ruleName);

    AwardRuleEntity queryAwardRuleByIdAndName(Long strategyId, Integer awardId, String ruleName);

    StrategyAwardRuleModelVO queryStrategyAwardRuleModels(Long strategyId, Integer awardId);

    RuleTreeVO queryRuleTreeVOByTreeId(String treeId);

    void awardStockConsumeSendQueue(StrategyAwardStockKeyVO strategyAwardStockKeyVO);

    void cacheStrategyAwardCount(Long strategyId, Integer awardId, Integer awardCount);

    StrategyAwardStockKeyVO getNextConsumedAwardFromQueue();

    Boolean decrRedisAwardCountByOne(String cacheKey);

    void decrDBAwardCountByOne(Long strategyId, Integer awardId);
}
