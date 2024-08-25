package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.infrastructure.persistent.po.StrategyAward;

import java.util.List;

@Mapper
public interface IStrategyAwardDAO {

    List<StrategyAward> queryStrategyAwardList();

    List<StrategyAward> queryStrategyAwardListById(Long strategyId);

    String queryStrategyAwardRuleModels(StrategyAward strategyAward);

    void decrAwardCount(@Param("strategyId") Long strategyId, @Param("awardId") Integer awardId, @Param("decrAmount") int decrAmount);

}
