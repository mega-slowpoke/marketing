package org.example.domain.strategy.repository;

import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import java.util.List;

public interface IStrategyRepo {

    List<StrategyAwardEntity> queryStrategyAwardEntityList();
}
