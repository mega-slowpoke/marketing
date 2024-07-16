package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.persistent.po.StrategyAward;

import java.util.List;

@Mapper
public interface IStrategyAwardDAO {

    List<StrategyAward> queryStrategyAwardList();

    List<StrategyAward> queryStrategyAwardListById(Long strategyId);
}
