package org.example.domain.strategy.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.domain.strategy.model.entity.*;
import org.example.domain.strategy.repository.IStrategyRepo;
import org.example.domain.strategy.service.executor.ILotteryExecutor;
import org.example.domain.strategy.service.filter.beforeFilter.factory.BeforeFilterFactory;
import org.example.domain.strategy.service.filter.treeFilter.factory.DefaultTreeFactory;
import org.example.types.common.Constants;
import org.example.types.enums.ResponseCode;
import org.example.types.exception.AppException;

import javax.annotation.Resource;

@Slf4j
public abstract class AbstractLotteryService implements ILotteryService, IStockService, IAwardService {

    @Resource
    protected IStrategyRepo iStrategyRepo;


    @Override
    public LotteryResEntity performLottery(LotteryReqEntity lotteryRequestEntity) {
        // template method
        Long strategyId = lotteryRequestEntity.getStrategyId();
        String userId = lotteryRequestEntity.getUserId();

        // 初始化最终结果
        LotteryResEntity lotteryRes = new LotteryResEntity();

        // 1. 检查
        if (null == strategyId || StringUtils.isBlank(userId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. 责任链抽奖计算【这步拿到的是初步的抽奖ID，之后需要根据ID处理抽奖】注意；黑名单、权重等非默认抽奖的直接返回抽奖结果
        BeforeFilterFactory.StrategyAwardVO chainStrategyAwardVO = beforeChainFilter(userId, strategyId);
        log.info("抽奖策略计算-责任链 {} {} {} {}", userId, strategyId, chainStrategyAwardVO.getAwardId(), chainStrategyAwardVO.getRuleModel());
        if (!Constants.RuleName.DEFAULT_RULE.equals(chainStrategyAwardVO.getRuleModel())) {
            // 如果有黑名单或者权重，黑名单和权重抽奖会返回奖品id
            lotteryRes.setAwardId(chainStrategyAwardVO.getAwardId());
            StrategyAwardEntity strategyAwardEntity = iStrategyRepo.queryStrategyAward(strategyId, chainStrategyAwardVO.getAwardId());
            lotteryRes.setSortOrder(strategyAwardEntity.getSortOrder());
            return lotteryRes;
        }

        // 3. 抽奖完毕，进行抽奖后规则树过滤【奖品ID，会根据抽奖次数判断、库存判断、兜底兜里返回最终的可获得奖品信息】
        DefaultTreeFactory.StrategyAwardVO treeStrategyAwardVO = afterTreeFilter(userId, strategyId, chainStrategyAwardVO.getAwardId());
        log.info("抽奖策略计算-规则树 {} {} {} {}", userId, strategyId, treeStrategyAwardVO.getAwardId(), treeStrategyAwardVO.getAwardRuleValue());

        lotteryRes.setAwardId(treeStrategyAwardVO.getAwardId());
        lotteryRes.setAwardConfig(treeStrategyAwardVO.getAwardRuleValue());
        StrategyAwardEntity strategyAwardEntity = iStrategyRepo.queryStrategyAward(strategyId, chainStrategyAwardVO.getAwardId());
        lotteryRes.setSortOrder(strategyAwardEntity.getSortOrder());

        // 4. 返回抽奖结果
        return lotteryRes;
    }


    public abstract BeforeFilterFactory.StrategyAwardVO beforeChainFilter(String userId, Long strategyId);

    /**
     * 抽奖结果过滤，决策树抽象方法
     *
     * @param userId     用户ID
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return 过滤结果【奖品ID，会根据抽奖次数判断、库存判断、兜底兜里返回最终的可获得奖品信息】
     */
    public abstract DefaultTreeFactory.StrategyAwardVO afterTreeFilter(String userId, Long strategyId, Integer awardId);
}
