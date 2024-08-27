package org.example.infrastructure.persistent.po;

import java.util.Date;

public class StrategyRule {
    private Long id;
    private Long strategyId;
    private String ruleModel;
    private String ruleValue;
    private String ruleDesc;
    private Date createTime;
    private Date updateTime;

    public StrategyRule() {
    }

    public StrategyRule(Long id, Long strategyId, String ruleModel, String ruleValue, String ruleDesc, Date createTime, Date updateTime) {
        this.id = id;
        this.strategyId = strategyId;
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
        return "StrategyRule{" +
                "id=" + id +
                ", strategyId=" + strategyId +
                ", ruleModel='" + ruleModel + '\'' +
                ", ruleValue='" + ruleValue + '\'' +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
