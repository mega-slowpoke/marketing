package org.example.domain.strategy.service.stock;

import org.springframework.stereotype.Service;

@Service
public interface IStockOperator {

    Boolean decrAwardStock(Long strategyId, Integer awardId);
}
