package org.example.domain.activity.service;

import org.example.domain.activity.model.entity.RaffleOrderReqEntity;
import org.example.domain.activity.model.entity.RaffleOrderEntity;

// Each time a user participates in a lottery, he / she is considered to have placed a lottery order.
public interface IRaffleOrderService {

    RaffleOrderEntity createRaffleActivityOrder(RaffleOrderReqEntity raffleOrderReqEntity);

}
