package org.example.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.valobj.RuleTreeVO;
import org.example.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
import org.example.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import org.example.domain.strategy.service.filter.beforeFilter.IBeforeFilter;
import org.example.domain.strategy.service.filter.beforeFilter.factory.BeforeFilterFactory;
import org.example.domain.strategy.service.filter.treeFilter.factory.DefaultTreeFactory;
import org.example.domain.strategy.service.filter.treeFilter.factory.engine.IDecisionTreeEngine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        DefaultTreeFactory.StrategyAwardVO strategyAwardVO = new DefaultTreeFactory.StrategyAwardVO();

        StrategyAwardRuleModelVO strategyAwardRuleModelVO = iStrategyRepo.queryStrategyAwardRuleModels(strategyId, awardId);
        // 如果没有after的过滤条件，就可以直接返回奖品了
        if (strategyAwardRuleModelVO == null) {
            strategyAwardVO.setAwardId(awardId);
            return strategyAwardVO;
        }

        String treeId = strategyAwardRuleModelVO.getRuleModels();
        RuleTreeVO ruleTreeVO = iStrategyRepo.queryRuleTreeVOByTreeId(treeId);
        IDecisionTreeEngine iDecisionTreeEngine = afterFilterFactory.openLogicTree(ruleTreeVO);
        return iDecisionTreeEngine.process(userId, strategyId, awardId);
    }

    @Override
    public StrategyAwardStockKeyVO getNextConsumedAwardFromQueue() throws InterruptedException {
        return iStrategyRepo.getNextConsumedAwardFromQueue();
    }

    @Override
    public void decrDBAwardCountByOne(Long strategyId, Integer awardId) {
        iStrategyRepo.decrDBAwardCountByOne(strategyId, awardId);
    }
}
