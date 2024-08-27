package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.persistent.po.Strategy;

import java.util.List;

@Mapper
public interface IStrategyDAO {

    List<Strategy> queryStrategyList();

    Strategy queryStrategyById(Long strategyId);
}
