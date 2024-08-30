package org.example.domain.activity.service;

import org.example.domain.activity.model.entity.*;
import org.example.domain.activity.repository.IRaffleOrderRepo;

import javax.annotation.Resource;

public class AbstractRaffleOrderService implements IRaffleOrderService{

    @Resource
    private IRaffleOrderRepo iRaffleOrderRepo;

    @Override
    public RaffleOrderEntity createRaffleActivityOrder(RaffleOrderReqEntity raffleOrderReqEntity) {
        // query the order info by skuId
        RaffleSkuEntity skuEntity = iRaffleOrderRepo.querySkuEntityById(raffleOrderReqEntity.getSkuId());

        // fill in order info
        RaffleOrderEntity raffleOrderEntity = new RaffleOrderEntity();

        ActivityEntity activityEntity = iRaffleOrderRepo.queryActivityEntityById(skuEntity.getActivityId());
        ActivityCountEntity activityCountEntity = iRaffleOrderRepo.queryActivityCountEntityById(skuEntity.getActivityCountId());

        return null;
    }

}
