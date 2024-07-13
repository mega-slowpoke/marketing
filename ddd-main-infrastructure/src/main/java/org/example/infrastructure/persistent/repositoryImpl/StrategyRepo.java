package org.example.infrastructure.persistent.repositoryImpl;

import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.infrastructure.persistent.dao.IStrategyAwardDAO;
import org.example.infrastructure.persistent.dao.IStrategyDAO;

import javax.annotation.Resource;
import java.util.List;

public class StrategyRepo implements IStrategyRepo {

    @Resource
    private IStrategyAwardDAO iStrategyAwardDAO;

    @Override
    public List<StrategyAwardEntity> queryStrategyAwardEntityList() {
        // query redis first
        List<StrategyAwardEntity>  strategyAwardEntity =

        // if redis not exists, query database
        return iStrategyAwardDAO.queryStrategyAwardList();

        // and update redis
    }
}
