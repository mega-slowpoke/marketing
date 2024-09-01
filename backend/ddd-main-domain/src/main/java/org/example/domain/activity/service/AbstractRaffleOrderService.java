package org.example.domain.activity.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.activity.model.entity.*;
import org.example.domain.activity.repository.IRaffleOrderRepo;

import javax.annotation.Resource;

@Slf4j
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

        log.info("查询结果：{} {} {}", JSON.toJSONString(skuEntity), JSON.toJSONString(activityEntity), JSON.toJSONString(activityCountEntity));

        return raffleOrderEntity;
    }

}
