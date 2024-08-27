package org.example.domain.strategy.model.entity;

public class LotteryReqEntity {

    private Long strategyId;
    private String userId;

    public LotteryReqEntity() {
    }

    public LotteryReqEntity(Long strategyId, String userId) {
        this.strategyId = strategyId;
        this.userId = userId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
