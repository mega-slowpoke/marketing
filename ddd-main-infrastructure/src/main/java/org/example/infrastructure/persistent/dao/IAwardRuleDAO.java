package org.example.infrastructure.persistent.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.infrastructure.persistent.po.AwardRule;

@Mapper
public interface IAwardRuleDAO {
    AwardRule queryAwardRuleByIdAndName(Long strategyId, Long awardId, String ruleName);
}
