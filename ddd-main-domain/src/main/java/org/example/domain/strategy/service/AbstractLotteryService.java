package org.example.domain.strategy.service;

import org.example.domain.strategy.model.entity.*;
import org.example.domain.strategy.model.valobj.StrategyAwardRuleModelVO;
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

        // 初始化返回结果
        LotteryResEntity lotteryRes =  new LotteryResEntity();
        lotteryRes.setUserId(userId);
        lotteryRes.setStrategyId(strategyId);

        StrategyEntity strategyEntity = iStrategyRepo.queryStrategyById(strategyId);
        // 检测抽奖前策略是否有特殊规则
        RuleActionEntity<RuleActionEntity.BeforeLotteryEntity> beforeAction = beforeLotteryFilter(lotteryRequestEntity, strategyEntity.getRuleModels());
        // 如果策略有特殊规则，执行特殊规则
        if (beforeAction.getCode().equals(RuleLogicCheckTypeVO.TAKE_OVER.getCode())) {
            // 用户要么是black_list，要么是权重抽奖
            if (beforeAction.getRuleModel().equals(Constants.RuleName.RULE_BLACKLIST)) {
                lotteryRes.setAwardId(beforeAction.getData().getBlackListAwardId());
                return lotteryRes;
            }
            // 权重抽奖
            if (beforeAction.getRuleModel().equals(Constants.RuleName.RULE_WEIGHT)) {
                Integer awardId = iLotteryExecutor.doLottery(strategyId, beforeAction.getData().getWeightValue());
                lotteryRes.setAwardId(awardId);
                return lotteryRes;
            }
        }

        // 如果抽奖前没有被特殊规则拦截，正常进行抽奖
        Integer awardId = iLotteryExecutor.doLottery(strategyId);
        lotteryRes.setAwardId(awardId);
        // 抽奖完毕后，查询奖品规则，检测是否抽中了未解锁奖品，进行过滤
        StrategyAwardRuleModelVO awardRuleEntities = iStrategyRepo.queryDuringAndAfterRuleModels(strategyId, awardId);

        RuleActionEntity<RuleActionEntity.DuringLotteryEntity> duringAction = duringLotteryFilter(lotteryRes, awardRuleEntities.duringRuleModels());
        if (duringAction.getCode().equals(RuleLogicCheckTypeVO.TAKE_OVER.getCode())) {
            // 待处理
            return new LotteryResEntity();
        }

        return lotteryRes;
    }



    // filter before lottery
    protected abstract RuleActionEntity<RuleActionEntity.BeforeLotteryEntity> beforeLotteryFilter(LotteryReqEntity lotteryReq, String... ruleModels);


    // filter in the middle of lottery (after getting the reward)
    protected abstract RuleActionEntity<RuleActionEntity.DuringLotteryEntity> duringLotteryFilter(LotteryResEntity lotteryReq, String... ruleModes);

    // filter after the lottery
//    protected
}
