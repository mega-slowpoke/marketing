package org.example.domain.strategy.model.entity;

public class StrategyEntity {
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖策略描述 */
    private String strategyDesc;

    /** 该抽奖策略所对应的相关抽奖规则 */
    private String ruleModels;

    public StrategyEntity() {
    }

    public StrategyEntity(Long strategyId, String strategyDesc, String ruleModels) {
        this.strategyId = strategyId;
        this.strategyDesc = strategyDesc;
        this.ruleModels = ruleModels;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getStrategyDesc() {
        return strategyDesc;
    }

    public void setStrategyDesc(String strategyDesc) {
        this.strategyDesc = strategyDesc;
    }

    public String getRuleModels() {
        return ruleModels;
    }

    public void setRuleModels(String ruleModels) {
        this.ruleModels = ruleModels;
    }
}

