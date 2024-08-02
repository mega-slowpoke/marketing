package org.example.infrastructure.persistent.repositoryImpl;

import org.example.domain.strategy.model.entity.AwardRuleEntity;
import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.model.entity.StrategyEntity;
import org.example.domain.strategy.model.entity.StrategyRuleEntity;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.infrastructure.persistent.dao.IAwardRuleDAO;
import org.example.infrastructure.persistent.dao.IStrategyAwardDAO;
import org.example.infrastructure.persistent.dao.IStrategyDAO;
import org.example.infrastructure.persistent.dao.IStrategyRuleDAO;
import org.example.infrastructure.persistent.po.AwardRule;
import org.example.infrastructure.persistent.po.Strategy;
import org.example.infrastructure.persistent.po.StrategyAward;
import org.example.infrastructure.persistent.po.StrategyRule;
import org.example.infrastructure.persistent.redis.IRedisService;
import org.example.types.common.Constants;
import org.redisson.api.RMap;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StrategyRepo implements IStrategyRepo {

    @Resource
    private IStrategyAwardDAO iStrategyAwardDAO;

    @Resource
    private IStrategyDAO iStrategyDAO;

    @Resource
    private IStrategyRuleDAO iStrategyRuleDAO;

    @Resource
    private IAwardRuleDAO iAwardRuleDAO;

    @Resource
    private IRedisService iRedisService;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardEntityList(Long strategyId) {
        String key = Constants.RedisKey.STRATEGY_AWARD_KEY + strategyId;
        List<StrategyAwardEntity> strategyAwardEntityList = iRedisService.getValue(key);

        // query redis first
        if (strategyAwardEntityList != null && !strategyAwardEntityList.isEmpty()) return strategyAwardEntityList;

        // if redis not exists, query database
        List<StrategyAward> strategyAwardList = iStrategyAwardDAO.queryStrategyAwardListById(strategyId);
        List<StrategyAwardEntity> res = new ArrayList<>();
        for (StrategyAward strategyAward : strategyAwardList) {
            StrategyAwardEntity strategyAwardEntity = new StrategyAwardEntity();
            strategyAwardEntity.setId(strategyAward.getId());
            strategyAwardEntity.setStrategyId(strategyAward.getStrategyId());
            strategyAwardEntity.setAwardId(strategyAward.getAwardId());
            strategyAwardEntity.setAwardTotal(strategyAward.getAwardTotal());
            strategyAwardEntity.setAwardRemaining(strategyAward.getAwardRemaining());
            strategyAwardEntity.setAwardRate(strategyAward.getAwardRate());
            res.add(strategyAwardEntity);
        }

        // and update redis
        iRedisService.setValue(key, res);
        return res;
    }

    @Override
    public void putAwardDistributionToRedis(String strategyKey, Integer totalBucket, Map<Integer, Integer> awardDistributionMap) {
        String bucketKey = Constants.RedisKey.STRATEGY_TOTAL_BUCKET_KEY + strategyKey;
        String mapKey = Constants.RedisKey.AWARD_DISTRIBUTION_KEY + strategyKey;

        // 存储rateRange和奖品分布
        iRedisService.setValue(bucketKey, totalBucket);
        RMap<Integer, Integer> cachedMap = iRedisService.getMap(mapKey);
        cachedMap.putAll(awardDistributionMap);
    }

    @Override
    public Integer getRange(String bucketNumberKey) {
        return iRedisService.getValue(bucketNumberKey);
    }

    @Override
    public Integer getAwardIdFromDistributionMap(String awardDistributionKey, Integer randomIdx) {
        return iRedisService.getFromMap(awardDistributionKey, randomIdx);
    }



    @Override
    public StrategyEntity queryStrategyById(Long strategyId) {
        String cacheKey = Constants.RedisKey.STRATEGY_KEY + strategyId;
        StrategyEntity strategyEntity = iRedisService.getValue(cacheKey);
        if (strategyEntity != null) return strategyEntity;
        Strategy strategy = iStrategyDAO.queryStrategyById(strategyId);
        strategyEntity = new StrategyEntity();
        strategyEntity.setStrategyId(strategy.getStrategyId());
        strategyEntity.setStrategyDesc(strategy.getStrategyDesc());
        strategyEntity.setRuleModels(strategy.getRuleModels().split(Constants.COMMA));
        iRedisService.setValue(cacheKey, strategyEntity);
        return strategyEntity;
    }

    @Override
    public StrategyRuleEntity queryStrategyRuleByIdAndName(Long strategyId, String ruleName) {
        StrategyRule strategyRule = iStrategyRuleDAO.queryStrategyRuleByIdAndName(strategyId, ruleName);
        StrategyRuleEntity strategyRuleEntity = new StrategyRuleEntity();
        strategyRuleEntity.setStrategyId(strategyRule.getStrategyId());
        strategyRuleEntity.setRuleModel(strategyRule.getRuleModel());
        strategyRuleEntity.setRuleValue(strategyRule.getRuleValue());
        strategyRuleEntity.setRuleDesc(strategyRule.getRuleDesc());
        return strategyRuleEntity;
    }

    @Override
    public AwardRuleEntity queryAwardRuleByIdAndName(Long strategyId, Long awardId, String ruleName) {
        AwardRule awardRule = iAwardRuleDAO.queryAwardRuleByIdAndName(strategyId, awardId, ruleName);
        AwardRuleEntity awardRuleEntity = new AwardRuleEntity();
        awardRuleEntity.setStrategyId(awardRule.getStrategyId());
        awardRuleEntity.setAwardId(awardRule.getAwardId());
        awardRuleEntity.setRuleModel(awardRule.getRuleModel());
        awardRuleEntity.setRuleValue(awardRule.getRuleValue());
        awardRuleEntity.setRuleDesc(awardRule.getRuleDesc());
        return awardRuleEntity;
    }

}
