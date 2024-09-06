package org.example.domain.activity.service;

import org.example.domain.activity.model.aggregate.OrderAggregate;
import org.example.domain.activity.model.entity.*;
import org.example.domain.activity.model.valobj.ActivityConstants;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RaffleOrderService extends AbstractRaffleOrderService {

    @Override
    protected OrderAggregate buildOrderAggregate(RaffleOrderReqEntity raffleOrderReqEntity, RaffleSkuEntity skuEntity, ActivityEntity activityEntity, ActivityCountEntity activityCountEntity) {
        // create RaffleOrderEntity
        RaffleOrderEntity raffleOrderEntity = new RaffleOrderEntity();
        raffleOrderEntity.setUserId(raffleOrderReqEntity.getUserId());
        raffleOrderEntity.setActivityId(raffleOrderEntity.getActivityId());
        raffleOrderEntity.setActivityName(activityEntity.getActivityName());
        raffleOrderEntity.setStrategyId(activityEntity.getStrategyId());
        raffleOrderEntity.setOrderId(raffleOrderEntity.getOrderId());
        raffleOrderEntity.setOrderTime(new Date());
        raffleOrderEntity.setTotalCount(activityCountEntity.getTotalCount());
        raffleOrderEntity.setDayCount(activityCountEntity.getDayCount());
        raffleOrderEntity.setMonthCount(activityCountEntity.getMonthCount());
        raffleOrderEntity.setState(ActivityConstants.State.COMPLETE);
        raffleOrderEntity.setBizId(raffleOrderReqEntity.getBizId());

        // build order aggregate
        OrderAggregate orderAggregate = new OrderAggregate();
        orderAggregate.setUserId(raffleOrderReqEntity.getUserId());
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
