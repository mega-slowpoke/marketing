package org.example.domain.strategy.service.filter.beforeFilter.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.strategy.model.entity.StrategyEntity;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.filter.beforeFilter.AbstractBeforeFilter;
import org.example.domain.strategy.service.filter.beforeFilter.IBeforeFilter;
import org.example.domain.strategy.service.filter.beforeFilter.impl.BlackListBeforeFilter;
import org.example.domain.strategy.service.filter.beforeFilter.impl.DefaultLotteryFilter;
import org.example.domain.strategy.service.filter.beforeFilter.impl.RuleWeightBeforeFilter;
import org.example.types.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Before filter, we create a linked list to do the filter
 *
 * */
@Service
public class BeforeFilterFactory {

    private Map<String, AbstractBeforeFilter> filterMap = new ConcurrentHashMap<>();

    @Resource
    private IStrategyRepo iStrategyRepo;

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void initializeFilters() {
        addNewFilter(Constants.RuleName.RULE_BLACKLIST, BlackListBeforeFilter.class);
        addNewFilter(Constants.RuleName.RULE_WEIGHT, RuleWeightBeforeFilter.class);
        addNewFilter(Constants.RuleName.DEFAULT_RULE, DefaultLotteryFilter.class);
    }

    private void addNewFilter(String ruleName, Class<? extends AbstractBeforeFilter> filterClass) {
        AbstractBeforeFilter filter = context.getBean(filterClass);
        filterMap.put(ruleName, filter);
    }


    /**
     * 实际采用的是AbstractBeforeFilter而不是IBeforeFilter，这样就可以把appendNext()和 next()方法隐藏起来，不需要
     * 放在IBeforeFilter中暴露给外界使用
     */
    public IBeforeFilter createFilterChain(Long strategyId) {
        StrategyEntity strategy = iStrategyRepo.queryStrategyById(strategyId);
        String[] ruleModels = strategy.getRuleModels();

        // 如果未配置策略规则，则只装填一个默认责任链
        if (null == ruleModels || 0 == ruleModels.length) return filterMap.get(Constants.RuleName.DEFAULT_RULE);

        // 按照配置顺序装填用户配置的责任链；rule_blacklist、rule_weight 「注意此数据从Redis缓存中获取，如果更新库表，记得在测试阶段手动处理缓存」
        AbstractBeforeFilter filterChainHead = filterMap.get(ruleModels[0]);
        AbstractBeforeFilter current = filterChainHead;
        for (int i = 1; i < ruleModels.length; i++) {
            AbstractBeforeFilter nextChain = filterMap.get(ruleModels[i]);
            current.appendNext(nextChain);
            current = current.next();
        }

        // 责任链的最后装填默认责任链
        current.appendNext(filterMap.get(Constants.RuleName.DEFAULT_RULE));

        return filterChainHead;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StrategyAwardVO {
        /** 抽奖奖品ID - 内部流转使用 */
        private Integer awardId;
        /**  */
        private String ruleModel;
    }
}
