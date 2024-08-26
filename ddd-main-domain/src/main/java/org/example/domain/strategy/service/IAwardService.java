package org.example.domain.strategy.service;

import org.example.domain.strategy.model.entity.StrategyAwardEntity;

import java.util.List;

public interface IAwardService {

    List<StrategyAwardEntity> getAwardList(Long strategyId);
}
