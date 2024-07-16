package org.example.domain.strategy.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class StrategyAwardEntity {
    private Long id;
    private Long StrategyId;
    private Integer awardId;
    private Integer awardTotal;
    private Integer awardRemaining;
    private BigDecimal awardRate;

    public StrategyAwardEntity() {
    }

    public StrategyAwardEntity(Long id, Long strategyId, Integer awardId, String awardDesc, Integer awardTotal, Integer awardRemaining, BigDecimal awardRate, String awardTitle, String awardSubtitle, String ruleModel) {
        this.id = id;
        StrategyId = strategyId;
        this.awardId = awardId;
        this.awardTotal = awardTotal;
        this.awardRemaining = awardRemaining;
        this.awardRate = awardRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStrategyId() {
        return StrategyId;
    }

    public void setStrategyId(Long strategyId) {
        StrategyId = strategyId;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public Integer getAwardTotal() {
        return awardTotal;
    }

    public void setAwardTotal(Integer awardTotal) {
        this.awardTotal = awardTotal;
    }

    public Integer getAwardRemaining() {
        return awardRemaining;
    }

    public void setAwardRemaining(Integer awardRemaining) {
        this.awardRemaining = awardRemaining;
    }

    public BigDecimal getAwardRate() {
        return awardRate;
    }

    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
    }
}
