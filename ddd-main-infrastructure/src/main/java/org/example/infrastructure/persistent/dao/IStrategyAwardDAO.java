package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.domain.strategy.model.entity.StrategyAwardEntity;
import org.example.infrastructure.persistent.po.StrategyAward;

import java.util.List;

@Mapper
public interface IStrategyAwardDAO {


    List<StrategyAward> queryStrategyAwardListById(Long strategyId);

    String queryStrategyAwardRuleModels(StrategyAward strategyAward);

    StrategyAward queryStrategyAward(Long strategyId, Integer awardId);

    void decrAwardCount(@Param("strategyId") Long strategyId, @Param("awardId") Integer awardId, @Param("decrAmount") int decrAmount);

}
