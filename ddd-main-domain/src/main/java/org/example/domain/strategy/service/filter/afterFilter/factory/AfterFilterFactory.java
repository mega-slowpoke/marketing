package org.example.domain.strategy.service.filter.afterFilter.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.strategy.service.filter.afterFilter.IAfterFilter;
import org.example.domain.strategy.service.filter.afterFilter.impl.BlackListFilter;
import org.example.domain.strategy.service.filter.afterFilter.impl.LotteryCountFilter;
import org.example.domain.strategy.service.filter.afterFilter.impl.WeightFilter;
import org.example.types.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AfterFilterFactory {

    private static Map<String, IAfterFilter<?>> filterMap = new ConcurrentHashMap<>();

    private static Map<String, String> filterType = new ConcurrentHashMap<>();

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void initializeFilters() {
        addNewFilter(Constants.RuleName.RULE_LOCK, LotteryCountFilter.class);

        filterType.put(Constants.RuleName.RULE_LOCK, Constants.RuleType.DURING_RULE);
        filterType.put(Constants.RuleName.RULE_LUCK_AWARD, Constants.RuleType.AFTER_RULE);
    }

    public void addNewFilter(String ruleName, Class<? extends IAfterFilter<?>> filterClass) {
        IAfterFilter<?> filter = context.getBean(filterClass);
        filterMap.put(ruleName, filter);
    }

    public IAfterFilter<?> getFilter(String ruleName) {
        return filterMap.get(ruleName);
    }

    public static boolean isAfterRule(String ruleName) {
        return Constants.RuleType.AFTER_RULE.equals(filterType.get(ruleName));
    }

    public static boolean isDuringRule(String ruleName) {
        return Constants.RuleType.DURING_RULE.equals(filterType.get(ruleName));
    }


}
