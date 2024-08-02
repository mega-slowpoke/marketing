package org.example.domain.strategy.model.entity;

import java.util.Date;

public class AwardRuleEntity {
    private Long strategyId;
    private Integer awardId;
    private String ruleModel;
    private String ruleValue;
    private String ruleDesc;

    public AwardRuleEntity() {
    }

    public AwardRuleEntity(Long strategyId, Integer awardId, String ruleModel, String ruleValue, String ruleDesc) {
        this.strategyId = strategyId;
        this.awardId = awardId;
        this.ruleModel = ruleModel;
        this.ruleValue = ruleValue;
        this.ruleDesc = ruleDesc;
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
}
