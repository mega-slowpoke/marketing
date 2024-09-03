package org.example.domain.activity.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.domain.activity.model.aggregate.OrderAggregate;
import org.example.domain.activity.model.entity.*;
import org.example.domain.activity.repository.IRaffleOrderRepo;
import org.example.types.enums.ResponseCode;
import org.example.types.exception.AppException;

import javax.annotation.Resource;

@Slf4j
public class AbstractRaffleOrderService implements IRaffleOrderService{

    @Resource
    private IRaffleOrderRepo iRaffleOrderRepo;



    @Override
    public String createRaffleOrder(RaffleOrderReqEntity raffleOrderReqEntity) {
        // parameter checkup
        String userId = raffleOrderReqEntity.getUserId();
        Long skuId = raffleOrderReqEntity.getSkuId();
        String bizId = raffleOrderReqEntity.getBizId();
        if (null == skuId || StringUtils.isBlank(userId) || StringUtils.isBlank(bizId)) {
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. fill in order info
        RaffleSkuEntity skuEntity = iRaffleOrderRepo.querySkuEntityById(skuId);
        RaffleOrderEntity raffleOrderEntity = new RaffleOrderEntity();
        ActivityEntity activityEntity = iRaffleOrderRepo.queryActivityEntityById(skuEntity.getActivityId());
        ActivityCountEntity activityCountEntity = iRaffleOrderRepo.queryActivityCountEntityById(skuEntity.getActivityCountId());

        log.info("查询结果：{} {} {}", JSON.toJSONString(skuEntity), JSON.toJSONString(activityEntity), JSON.toJSONString(activityCountEntity));

        // rule filter


        // create order aggregate object

        // save the order info

        return null;
    }

}
