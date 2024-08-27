package org.example.infrastructure.persistent.po;

import java.math.BigDecimal;
import java.util.Date;

public class StrategyAward {
    private Long id;
    private Long StrategyId;
    private Integer awardId;
    private Integer awardTotal;
    private Integer awardRemaining;
    private BigDecimal awardRate;
    private String awardTitle;
    private String awardSubtitle;
    private String ruleModel;
    private Integer sortOrder;
    private Date createTime;
    private Date updateTime;

    public StrategyAward() {
    }

    public StrategyAward(Long id, Long strategyId, Integer awardId, Integer awardTotal, Integer awardRemaining, BigDecimal awardRate, String awardTitle, String awardSubtitle, String ruleModel, Integer sortOrder, Date createTime, Date updateTime) {
        this.id = id;
        StrategyId = strategyId;
        this.awardId = awardId;
        this.awardTotal = awardTotal;
        this.awardRemaining = awardRemaining;
        this.awardRate = awardRate;
        this.awardTitle = awardTitle;
        this.awardSubtitle = awardSubtitle;
        this.ruleModel = ruleModel;
        this.sortOrder = sortOrder;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getRuleModel() {
        return ruleModel;
    }

    public void setRuleModel(String ruleModel) {
        this.ruleModel = ruleModel;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "StrategyAward{" +
                "id=" + id +
                ", StrategyId=" + StrategyId +
                ", awardId=" + awardId +
                ", awardTotal=" + awardTotal +
                ", awardRemaining=" + awardRemaining +
                ", awardRate=" + awardRate +
                ", awardTitle='" + awardTitle + '\'' +
                ", awardSubtitle='" + awardSubtitle + '\'' +
                ", ruleModel='" + ruleModel + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
