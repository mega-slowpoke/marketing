package org.example.api.DTO;

import lombok.Data;

@Data
public class LotteryRequestDTO {
    // 抽奖策略ID
    private Long strategyId;

    private String userId;
}
