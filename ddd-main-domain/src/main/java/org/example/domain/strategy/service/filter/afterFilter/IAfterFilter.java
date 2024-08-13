package org.example.domain.strategy.service.filter.afterFilter;

import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.entity.FilterConditionEntity;

public interface IAfterFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(FilterConditionEntity filterConditionEntity);

}
