package org.example.infrastructure.persistent.repositoryImpl;

import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.infrastructure.persistent.dao.IStrategyAwardDAO;
import org.example.infrastructure.persistent.po.StrategyAward;
import org.example.infrastructure.persistent.redis.IRedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StrategyRepo implements IStrategyRepo {

    @Resource
    private IStrategyAwardDAO iStrategyAwardDAO;

    @Resource
    private IRedisService iRedisService;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardEntityList(Long strategyId) {
        String key = "strategy:" + strategyId;
        List<StrategyAwardEntity> strategyAwardEntityList = iRedisService.getValue(key);

        // query redis first
        if (strategyAwardEntityList != null && !strategyAwardEntityList.isEmpty()) return strategyAwardEntityList;

        // if redis not exists, query database
        List<StrategyAward> strategyAwardList  =  iStrategyAwardDAO.queryStrategyAwardListById(strategyId);
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
}
