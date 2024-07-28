package org.example.domain.strategy.service.filter.factory;

import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.service.filter.IFilter;
import org.example.domain.strategy.service.filter.impl.BlackListFilter;
import org.example.domain.strategy.service.filter.impl.WeightFilter;
import org.example.types.common.Constants;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FilterFactory {

    private Map<String, IFilter<?>> filterMap = new ConcurrentHashMap<>();

    public FilterFactory(Map<String, IFilter<?>> filterMap) {
        addNewFilter(Constants.RuleName.RULE_BLACKLIST, new BlackListFilter());
        addNewFilter(Constants.RuleName.RULE_WEIGHT, new WeightFilter());
    }

    public IFilter<?> createLFilter(String ruleName) {
        return filterMap.get(ruleName);

    }

    public void addNewFilter(String ruleName, IFilter<?> newFilter) {
        filterMap.put(ruleName, newFilter);
    }

}
