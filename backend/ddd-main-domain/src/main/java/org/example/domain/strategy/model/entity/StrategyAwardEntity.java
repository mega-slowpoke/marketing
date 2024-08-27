package org.example.domain.strategy.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class StrategyAwardEntity {
    private Long StrategyId;
    private String awardTitle;
    private String awardSubtitle;
    private Integer awardId;
    private Integer awardTotal;
    private Integer awardRemaining;
    private BigDecimal awardRate;
    private Integer sortOrder;


    public StrategyAwardEntity() {
    }

    public StrategyAwardEntity(Long strategyId, String awardTitle, String awardSubtitle, Integer awardId, Integer awardTotal, Integer awardRemaining, BigDecimal awardRate, String ruleModel, Integer sortOrder) {
        StrategyId = strategyId;
        this.awardTitle = awardTitle;
        this.awardSubtitle = awardSubtitle;
        this.awardId = awardId;
        this.awardTotal = awardTotal;
        this.awardRemaining = awardRemaining;
        this.awardRate = awardRate;
        this.sortOrder = sortOrder;
    }


    public Long getStrategyId() {
        return StrategyId;
    }

    public void setStrategyId(Long strategyId) {
        StrategyId = strategyId;
    }

    public String getAwardTitle() {
        return awardTitle;
    }

    public void setAwardTitle(String awardTitle) {
        this.awardTitle = awardTitle;
    }

    public String getAwardSubtitle() {
        return awardSubtitle;
    }

    public void setAwardSubtitle(String awardSubtitle) {
        this.awardSubtitle = awardSubtitle;
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

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
