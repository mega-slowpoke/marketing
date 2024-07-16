package org.example.domain.strategy.model.entity;

import org.example.types.common.Constants;

import java.util.*;
import java.util.stream.Collectors;

public class StrategyRuleEntity {
    private Long strategyId;
    private String ruleModel;
    private String ruleValue;
    private String ruleDesc;

    private Map<Integer, List<Integer>> ruleWeightMap;

    public StrategyRuleEntity() {
    }

    public StrategyRuleEntity(Long strategyId, String ruleModel, String ruleValue, String ruleDesc) {
        this.strategyId = strategyId;
        this.ruleModel = ruleModel;
        this.ruleValue = ruleValue;
        this.ruleDesc = ruleDesc;
        this.ruleWeightMap = buildRuleWeightMap(ruleValue);
    }

    private Map<Integer, List<Integer>> buildRuleWeightMap(String ruleValue) {
        // ["4000:102,103,104" , "5000:103,104,105" , "6000:..."]
        Map<Integer, List<Integer>> res = new HashMap<>();
        String[] rules = ruleValue.split(Constants.SEMICOLON);
        for (String rule : rules) {
            // ["4000" , "102,103,104"]
            String[] separateKeyAndVars = rule.split(Constants.COLON);
            String key = separateKeyAndVars[0];
            String[] vars = separateKeyAndVars[1].split(Constants.COMMA);
            res.put(Integer.parseInt(key), Arrays.stream(vars)
                                                .map(Integer::parseInt)
                                                .collect(Collectors.toList()));
        }
        return res;
    }

    public Map<Integer, List<Integer>> getRuleWeightMap() {
        return ruleModel.equals("rule_weight") ? ruleWeightMap : null;
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
