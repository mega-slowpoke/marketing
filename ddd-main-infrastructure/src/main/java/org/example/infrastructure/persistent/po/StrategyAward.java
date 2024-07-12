package org.example.infrastructure.persistent.po;

import java.math.BigDecimal;
import java.util.Date;

public class StrategyAward {
    private Long Id;
    private Long StrategyId;
    private Long awardId;
    private String awardDesc;
    private Integer awardTotal;
    private Integer awardRemaining;
    private BigDecimal awardRate;
    private String awardTitle;
    private String awardSubtitle;
    private String ruleModel;
    private String sortOrder;
    private Date createTime;
    private Date updateTime;


    public StrategyAward(Long id, Long strategyId, Long awardId, String awardDesc, Integer awardTotal, Integer awardRemaining, BigDecimal awardRate, String awardTitle, String awardSubtitle, String ruleModel, String sortOrder, Date createTime, Date updateTime) {
        Id = id;
        StrategyId = strategyId;
        this.awardId = awardId;
        this.awardDesc = awardDesc;
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
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getStrategyId() {
        return StrategyId;
    }

    public void setStrategyId(Long strategyId) {
        StrategyId = strategyId;
    }

    public Long getAwardId() {
        return awardId;
    }

    public void setAwardId(Long awardId) {
        this.awardId = awardId;
    }

    public String getAwardDesc() {
        return awardDesc;
    }

    public void setAwardDesc(String awardDesc) {
        this.awardDesc = awardDesc;
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

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
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
}
