package org.example.domain.activity.service;

import org.example.domain.activity.model.entity.RechargeReqEntity;

// Each time a user participates in a lottery, he / she is considered to have placed a lottery order.
public interface IRaffleOrderService {


    String rechargeUserBalance(RechargeReqEntity rechargeReqEntity);
}
