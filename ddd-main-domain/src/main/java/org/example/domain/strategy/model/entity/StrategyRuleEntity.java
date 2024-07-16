package org.example.domain.strategy.model.entity;

public class StrategyRuleEntity {
    private Long strategyId;
    private String ruleModel;
    private String ruleValue;
    private String ruleDesc;

    public StrategyRuleEntity() {
    }

    public StrategyRuleEntity(Long strategyId, String ruleModel, String ruleValue, String ruleDesc) {
        this.strategyId = strategyId;
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

    @Override
    public String toString() {
        return "StrategyRuleEntity{" +
                "strategyId=" + strategyId +
                ", ruleModel='" + ruleModel + '\'' +
                ", ruleValue='" + ruleValue + '\'' +
                ", ruleDesc='" + ruleDesc + '\'' +
                '}';
    }
}
