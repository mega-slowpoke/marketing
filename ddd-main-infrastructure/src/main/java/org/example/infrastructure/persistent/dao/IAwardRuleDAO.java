package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.infrastructure.persistent.po.AwardRule;

@Mapper
public interface IAwardRuleDAO {
    AwardRule queryAwardRuleByIdAndName(@Param("strategyId") Long strategyId, @Param("awardId") Integer awardId, @Param("ruleName") String ruleName);
}
