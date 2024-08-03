package org.example.domain.strategy.service.filter.factory;

import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.service.filter.IFilter;
import org.example.domain.strategy.service.filter.impl.BlackListFilter;
import org.example.domain.strategy.service.filter.impl.LotteryCountFilter;
import org.example.domain.strategy.service.filter.impl.WeightFilter;
import org.example.types.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FilterFactory {

    private Map<String, IFilter<?>> filterMap = new ConcurrentHashMap<>();

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void initializeFilters() {
        addNewFilter(Constants.RuleName.RULE_BLACKLIST, BlackListFilter.class);
        addNewFilter(Constants.RuleName.RULE_WEIGHT, WeightFilter.class);
        addNewFilter(Constants.RuleName.RULE_LOCK, LotteryCountFilter.class);
    }

    public void addNewFilter(String ruleName, Class<? extends IFilter<?>> filterClass) {
        IFilter<?> filter = context.getBean(filterClass);
        filterMap.put(ruleName, filter);
    }

    public IFilter<?> getFilter(String ruleName) {
        return filterMap.get(ruleName);
    }

}
