package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.infrastructure.persistent.po.AwardRule;

import java.util.List;

@Mapper
public interface IAwardRuleDAO {
    AwardRule queryAwardRuleByIdAndName(@Param("strategyId") Long strategyId, @Param("awardId") Integer awardId, @Param("ruleName") String ruleName);

    List<AwardRule> queryAwardRulesById(@Param("strategyId") Long strategyId, @Param("awardId") Integer awardId);


}
