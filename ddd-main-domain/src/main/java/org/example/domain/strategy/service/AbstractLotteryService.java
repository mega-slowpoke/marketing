package org.example.domain.strategy.service;

import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;
import org.example.domain.strategy.model.entity.RuleActionEntity;
import org.example.domain.strategy.model.entity.StrategyEntity;
import org.example.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.executor.ILotteryExecutor;
import org.example.types.common.Constants;

import javax.annotation.Resource;

public abstract class AbstractLotteryService implements ILotteryService {

    @Resource
    protected IStrategyRepo iStrategyRepo;

    @Resource
    protected ILotteryExecutor iLotteryExecutor;

    @Override
    public LotteryResEntity performLottery(LotteryReqEntity lotteryRequestEntity) {
        // template method
        Long strategyId = lotteryRequestEntity.getStrategyId();
        String userId = lotteryRequestEntity.getUserId();

        StrategyEntity strategyEntity = iStrategyRepo.queryStrategyById(strategyId);

        // 检测策略是否有特殊规则
        RuleActionEntity<RuleActionEntity.BeforeLotteryEntity> action = beforeLotteryFilter(lotteryRequestEntity, strategyEntity.getRuleModels());

        // 初始化返回结果
        LotteryResEntity res =  new LotteryResEntity();
        res.setStrategyId(strategyId);

        // 如果策略有特殊规则，执行特殊规则
        if (action.getCode().equals(RuleLogicCheckTypeVO.TAKE_OVER.getCode())) {
            // 用户要么是black_list，要么是权重抽奖
            if (action.getRuleModel().equals(Constants.RuleName.RULE_BLACKLIST)) {
                res.setAwardId(action.getData().getBlackListAwardId());
                return res;
            }
            // 权重抽奖
            if (action.getRuleModel().equals(Constants.RuleName.RULE_WEIGHT)) {
                Integer awardId = iLotteryExecutor.doLottery(strategyId, action.getData().getWeightValue());
                res.setAwardId(awardId);
                return res;
            }

        }

        // 不然的话，就没有特殊规则
        Integer awardId = iLotteryExecutor.doLottery(strategyId);
        res.setAwardId(awardId);
        return res;
    }



    // filter before lottery
    protected abstract RuleActionEntity<RuleActionEntity.BeforeLotteryEntity> beforeLotteryFilter(LotteryReqEntity lotteryReq, String... ruleModels);


    // filter in the middle of lottery
    protected abstract RuleActionEntity<RuleActionEntity.DuringLotteryEntity> duringLotteryFilter(LotteryReqEntity lotteryReq, String... ruleModes);

    // filter after the lottery

}
