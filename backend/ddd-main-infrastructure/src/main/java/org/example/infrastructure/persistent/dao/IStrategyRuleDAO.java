package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.infrastructure.persistent.po.StrategyAward;
import org.example.infrastructure.persistent.po.StrategyRule;

import java.util.List;

@Mapper
public interface IStrategyRuleDAO {

    List<StrategyRule> queryStrategyRuleList();

    StrategyRule queryStrategyRuleByIdAndName(@Param("strategyId") Long strategyId, @Param("ruleModel") String ruleModel);

}
