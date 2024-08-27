package org.example.domain.strategy.model.entity;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.example.types.common.Constants;

import java.util.Arrays;

public class StrategyEntity {
    /** 抽奖策略ID */
    private Long strategyId;
    /** 抽奖策略描述 */
    private String strategyDesc;

    /** 该抽奖策略所对应的相关抽奖规则 */
    private String[] ruleModels;

    private Boolean ruleWeight = false;

    public StrategyEntity() {
    }

    public StrategyEntity(Long strategyId, String strategyDesc, String[] ruleModels) {
        this.strategyId = strategyId;
        this.strategyDesc = strategyDesc;
        this.ruleModels = ruleModels;
        checkRuleHasWeight();
    }

    private void checkRuleHasWeight() {
        for (String rule : ruleModels) {
            if (rule.equals("rule_weight")) {
                ruleWeight = true;
                break;
            }
        }
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

    public String[] getRuleModels() {
        return ruleModels;
    }

    public void setRuleModels(String ruleModelStr) {
        if (ruleModelStr == null) {
            this.ruleModels = null;
            return;
        }

        this.ruleModels = ruleModelStr.split(Constants.COMMA);
        checkRuleHasWeight();
    }

    public String getRuleWeight() {
        return this.ruleWeight? "rule_weight" : null;
    }

    @Override
    public String toString() {
        return "StrategyEntity{" +
                "strategyId=" + strategyId +
                ", strategyDesc='" + strategyDesc + '\'' +
                ", ruleModels=" + Arrays.toString(ruleModels) +
                ", ruleWeight=" + ruleWeight +
                '}';
    }
}

