package org.example.domain.strategy.service.filter;

import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.entity.FilterConditionEntity;

public interface IFilter<T extends RuleActionEntity.RaffleEntity> {

    RuleActionEntity<T> filter(FilterConditionEntity filterConditionEntity);

}