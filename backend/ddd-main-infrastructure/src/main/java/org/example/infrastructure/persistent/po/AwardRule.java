package org.example.infrastructure.persistent.po;

import java.util.Date;

public class AwardRule {
    private Long id;
    private Long strategyId;
    private Integer awardId;
    private String ruleModel;
    private String ruleValue;
    private String ruleDesc;
    private Date createTime;
    private Date updateTime;

    public AwardRule() {
    }

    public AwardRule(Long id, Long strategyId, Integer awardId, String ruleModel, String ruleValue, String ruleDesc, Date createTime, Date updateTime) {
        this.id = id;
        this.strategyId = strategyId;
        this.awardId = awardId;
        this.ruleModel = ruleModel;
        this.ruleValue = ruleValue;
        this.ruleDesc = ruleDesc;
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
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getRuleModel() {
        return ruleModel;
    }

    public void setRuleModel(String ruleModel) {
        this.ruleModel = ruleModel;
    }

    public String getRuleValue() {
        return ruleValue;
    }

    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
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
        return "AwardRule{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", awardId=" + awardId +
                ", ruleModel='" + ruleModel + '\'' +
                ", ruleValue='" + ruleValue + '\'' +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
