package org.example.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.entity.FilterConditionEntity;
import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;
import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.model.valobj.RuleTreeVO;
import org.example.domain.strategy.service.filter.afterFilter.IAfterFilter;
import org.example.domain.strategy.service.filter.afterFilter.factory.AfterFilterFactory;
import org.example.domain.strategy.service.filter.beforeFilter.IBeforeFilter;
import org.example.domain.strategy.service.filter.beforeFilter.factory.BeforeFilterFactory;
import org.example.domain.strategy.service.filter.treeFilter.factory.DefaultTreeFactory;
import org.example.types.common.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

@Slf4j
@Service
public class DefaultLotteryService extends AbstractLotteryService {

    @Resource
    private BeforeFilterFactory beforeFilterFactory;

    @Resource
    private DefaultTreeFactory afterFilterFactory;

    @Override
    public BeforeFilterFactory.StrategyAwardVO beforeChainFilter(String userId, Long strategyId) {
        IBeforeFilter chainFilter = beforeFilterFactory.createFilterChain(strategyId);
        return chainFilter.processBeforeFilterAndGetAwardId(userId, strategyId);
    }

    @Override
    public DefaultTreeFactory.StrategyAwardVO afterTreeFilter(String userId, Long strategyId, Integer awardId) {
//        RuleTreeVO ruleTreeVO = iStrategyRepo.query
//
//        IAfterFilter treeFilter = afterFilterFactory.openLogicTree().
        return null;
    }

}
