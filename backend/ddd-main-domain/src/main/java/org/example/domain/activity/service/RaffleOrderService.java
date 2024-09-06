package org.example.domain.activity.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.domain.activity.model.aggregate.OrderAggregate;
import org.example.domain.activity.model.entity.*;
import org.example.domain.activity.model.valobj.ActivityConstants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RaffleOrderService extends AbstractRaffleOrderService {

    @Override
    protected OrderAggregate buildOrderAggregate(RechargeReqEntity rechargeReqEntity, RaffleSkuEntity skuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        // create RaffleOrderEntity
        RaffleOrderEntity raffleOrderEntity = new RaffleOrderEntity();
        raffleOrderEntity.setUserId(rechargeReqEntity.getUserId());
        raffleOrderEntity.setSkuId(skuEntity.getSku());
        raffleOrderEntity.setActivityId(activityEntity.getActivityId());
        raffleOrderEntity.setActivityName(activityEntity.getActivityName());
        raffleOrderEntity.setStrategyId(activityEntity.getStrategyId());
        raffleOrderEntity.setOrderId(RandomStringUtils.randomNumeric(12));
        raffleOrderEntity.setOrderTime(new Date());
        raffleOrderEntity.setTotalCount(activityCountEntity.getTotalCount());
        raffleOrderEntity.setDayCount(activityCountEntity.getDayCount());
        raffleOrderEntity.setMonthCount(activityCountEntity.getMonthCount());
        raffleOrderEntity.setState(ActivityConstants.State.COMPLETE);
        raffleOrderEntity.setOutBusinessNo(rechargeReqEntity.getOutBusinessNo());

        // build order aggregate
        OrderAggregate orderAggregate = new OrderAggregate();
        orderAggregate.setUserId(rechargeReqEntity.getUserId());
        orderAggregate.setActivityId(activityEntity.getActivityId());
        orderAggregate.setTotalCount(activityCountEntity.getTotalCount());
        orderAggregate.setDayCount(activityCountEntity.getDayCount());
        orderAggregate.setMonthCount(activityCountEntity.getMonthCount());
        orderAggregate.setActivityOrderEntity(raffleOrderEntity);
        return orderAggregate;
    }

    @Override
    protected void saveOrder(OrderAggregate orderAggregate) {
        iRaffleOrderRepo.saveOrder(orderAggregate);
    }
}
