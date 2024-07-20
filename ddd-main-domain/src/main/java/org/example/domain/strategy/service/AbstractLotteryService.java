package org.example.domain.strategy.service;

import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;
import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.entity.StrategyEntity;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.executor.ILotteryExecutor;
import org.example.domain.strategy.service.initializer.IStrategyInitializer;

import javax.annotation.Resource;

public abstract class AbstractLotteryService implements ILotteryService {

    @Resource
    protected IStrategyRepo iStrategyRepo;

    @Resource
    protected IStrategyInitializer iStrategyInitializer;

    @Resource
    protected ILotteryExecutor iLotteryExecutor;

    @Override
    public LotteryResEntity performLottery(LotteryReqEntity lotteryRequestEntity) {
        // template method
        Long strategyId = lotteryRequestEntity.getStrategyId();
        String userId = lotteryRequestEntity.getUserId();

        StrategyEntity strategyEntity = iStrategyRepo.queryStrategyById(strategyId);

        // 检测策略是否有特殊规则
        RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> beforeEntityRuleActionEntity = checkRaffleBeforeLogic(lotteryRequestEntity, strategyEntity.getRuleModels());

        // 如果策略有特殊规则，执行特殊规则
        if (beforeEntityRuleActionEntity.getCode().equals("10001")) {
            // 用户要么是black_list，要么是采用了特殊的权重抽奖
            if (beforeEntityRuleActionEntity.getRuleModel().equals("rule_blacklist")) {

            }

            if (beforeEntityRuleActionEntity.getRuleModel().equals("rule_weight")) {

            }

        }


        // 不然的话，就没有特殊规则

        return null;
    }



    // filter before lottery
    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> checkRaffleBeforeLogic(LotteryReqEntity lotteryReq, String... ruleModels);


    // filter in the middle of lottery


    // filter after the lottery

}
