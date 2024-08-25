package org.example.domain.strategy.service.filter.treeFilter.impl;


import lombok.extern.slf4j.Slf4j;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.filter.treeFilter.ILogicTreeNode;
import org.example.domain.strategy.service.filter.treeFilter.factory.DefaultTreeFactory;
import org.example.domain.strategy.service.stock.IStockOperator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Fuzhengwei bugstack.cn @小傅哥
 * @description 库存扣减节点
 * @create 2024-01-27 11:25
 */
@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {
    @Resource
    private IStrategyRepo iStrategyRepo;

    @Resource
    private IStockOperator iStockOperator;

    @Override
    public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId, String ruleValue) {
        log.info("规则过滤-库存扣减 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        Boolean decrStatus = iStockOperator.decrRedisAwardCount(strategyId, awardId);

        // true；库存扣减成功，TAKE_OVER 规则节点接管，返回奖品ID，奖品规则配置
        if (decrStatus) {
            log.info("规则过滤-库存扣减-成功 userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);

            // 写入延迟队列，延迟消费更新数据库记录。【在trigger的job；UpdateAwardStockJob 下消费队列，更新数据库记录】
            iStrategyRepo.awardStockConsumeSendQueue(StrategyAwardStockKeyVO.builder()
                    .strategyId(strategyId)
                    .awardId(awardId)
                    .build());

            return DefaultTreeFactory.TreeActionEntity.builder()
                    .ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
                    .strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
                            .awardId(awardId)
                            .awardRuleValue(ruleValue)
                            .build())
                    .build();
        }

        // 如果库存不足，则放行，由luck_award保底奖品来处理该节点
        log.warn("规则过滤-库存扣减-告警，库存不足。userId:{} strategyId:{} awardId:{}", userId, strategyId, awardId);
        return DefaultTreeFactory.TreeActionEntity.builder()
                .ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
                .build();
    }
}
