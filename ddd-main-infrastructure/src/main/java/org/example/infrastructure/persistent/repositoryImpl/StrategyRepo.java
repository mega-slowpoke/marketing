package org.example.infrastructure.persistent.repositoryImpl;

import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.infrastructure.persistent.dao.IStrategyAwardDAO;
import org.example.infrastructure.persistent.po.StrategyAward;
import org.example.infrastructure.persistent.redis.IRedisService;
import org.example.types.common.Constants;
import org.redisson.api.RMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StrategyRepo implements IStrategyRepo {

    @Resource
    private IStrategyAwardDAO iStrategyAwardDAO;

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
    public void putAwardDistributionToRedis(Long strategyId, BigDecimal rateRange, Map<Integer, Integer> awardDistributionMap) {
        String rateKey = Constants.RedisKey.STRATEGY_RATE_RANGE_KEY + strategyId;
        String mapKey = Constants.RedisKey.STRATEGY_RATE_TABLE_KEY + strategyId;

        // 存储rateRange和奖品分布
        iRedisService.setValue(rateKey, rateRange.intValue());
        RMap<Integer, Integer> cachedMap = iRedisService.getMap(mapKey);
        cachedMap.putAll(awardDistributionMap);
    }

    @Override
    public Integer getRateRange(Long strategyId) {
        return null;
    }
}
