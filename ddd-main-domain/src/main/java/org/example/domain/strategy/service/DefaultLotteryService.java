package org.example.domain.strategy.service;

import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;
import org.example.domain.strategy.model.entity.RuleActionEntity;

public class DefaultLotteryService extends AbstractLotteryService {


    @Override
    protected RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> checkRaffleBeforeLogic(LotteryReqEntity lotteryReq, String... logics) {
        return null;
    }
}
