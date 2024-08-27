package org.example.domain.strategy.service;

import org.example.domain.strategy.model.entity.LotteryReqEntity;
import org.example.domain.strategy.model.entity.LotteryResEntity;

/**
 * This is the only interface exposed to outside
 */
public interface ILotteryService {

    LotteryResEntity performLottery(LotteryReqEntity lotteryRequestEntity);

}
