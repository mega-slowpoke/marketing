package org.example.domain.activity.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.domain.activity.model.aggregate.OrderAggregate;
import org.example.domain.activity.model.entity.*;
import org.example.domain.activity.repository.IRaffleOrderRepo;
import org.example.types.enums.ResponseCode;
import org.example.types.exception.AppException;

import javax.annotation.Resource;

@Slf4j
public abstract class AbstractRaffleOrderService implements IRaffleOrderService{

    @Resource
    protected IRaffleOrderRepo iRaffleOrderRepo;


    @Override
    public String rechargeUserBalance(RechargeReqEntity rechargeReqEntity) {
        // parameter checkup
        String userId = rechargeReqEntity.getUserId();
        Long skuId = rechargeReqEntity.getSkuId();
        String bizId = rechargeReqEntity.getOutBusinessNo();
        if (null == skuId || StringUtils.isBlank(userId) || StringUtils.isBlank(bizId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. fill in order info
        RaffleSkuEntity skuEntity = iRaffleOrderRepo.querySkuEntityById(skuId);
        ActivityEntity activityEntity = iRaffleOrderRepo.queryActivityEntityById(skuEntity.getActivityId());
        ActivityCountEntity activityCountEntity = iRaffleOrderRepo.queryActivityCountEntityById(skuEntity.getActivityCountId());


        // rule filter
        // TODO

        // create order aggregate object
        OrderAggregate orderAggregate = buildOrderAggregate(rechargeReqEntity, skuEntity, activityEntity, activityCountEntity);

        // save the order info, increment user's available balance
        saveOrder(orderAggregate);

        return orderAggregate.getActivityOrderEntity().getOrderId();
    }

    protected abstract OrderAggregate buildOrderAggregate(RechargeReqEntity rechargeReqEntity, RaffleSkuEntity skuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity);

    protected abstract void saveOrder(OrderAggregate orderAggregate);

}
