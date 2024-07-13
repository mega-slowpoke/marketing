package org.example.domain.strategy.service;

import org.example.domain.strategy.model.entity.StrategyAwardEntity;

import javax.annotation.Resource;

public class StrategyInitializer implements IStrategyInitializer{

    @Resource
    private IS

    @Override
    public void initializeStrategy(Integer strategyId) {
        // 1. get strategy awards
        List<StrategyAwardEntity> strategyAwardEntityList = query


        // 2. create buckets for all award

        // 3. put awards to corresponding buckets

        // 4. move map to redis
    }
}
